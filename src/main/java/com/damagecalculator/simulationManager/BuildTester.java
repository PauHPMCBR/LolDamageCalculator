package com.damagecalculator.simulationManager;

import java.util.*;

import com.damagecalculator.GlobalVariables;
import com.damagecalculator.simulationManager.simulation.*;

/**
 * Class designed to test item combinations and compare them
 */
public class BuildTester {
    /**
     * Class made to sort builds by performance
     */
    static class BuildScore {
        float score;
        List<Item> build;
        int cost;
        public BuildScore(float score, List<Item> build) {
            this.score = score;
            this.build = build;
            cost = 0;
            for (Item i : build) {
                cost += i.cost;
            }
        }
    }
    private static class BuildScoreComparator implements Comparator<BuildScore> {
        boolean greater;
        public int compare(BuildScore a1, BuildScore a2) {
            if (a1.score != a2.score) {
                if (greater) return (a1.score < a2.score) ? 1 : -1;
                else return (a1.score > a2.score) ? 1 : -1;
            }
            if (a1.cost != a2.cost)
                return (a1.cost > a2.cost) ? 1 : -1;
            if (a1.build.size() != a2.build.size())
                return (a1.build.size() > a2.build.size()) ? 1 : -1;

            return 0;
        }
        public BuildScoreComparator(boolean greater) {
            this.greater = greater;
        }
    }

    private RunePage runes;

    public Printer printer = new Printer();

    private int maxItems;
    private int maxCost;
    private List<Item> permanentItems; //items that will be present in every test
    private List<Item> variableItems; //items that can be part of a combination for a build complementing the permanentItems

    private Set<Inventory> combinations;
    private void generateCombinations(Inventory combination, int pos, int size, int cost) {
        if (pos == variableItems.size()) return;
        combinations.add(new Inventory(combination));

        if (combination.add(variableItems.get(pos))) {
            ++size;
            cost += variableItems.get(pos).cost;
            if (cost <= maxCost && size <= maxItems)
                generateCombinations(combination, pos, size, cost);
            combination.remove(variableItems.get(pos));
            --size;
            cost -= variableItems.get(pos).cost;
        }
        generateCombinations(combination, pos+1, size, cost);
    }
    private void generateCombinations() {
        combinations = new HashSet<>();
        Inventory starter = new Inventory();
        int startingCost = 0;
        int startingSize = 0;
        for (Item i : permanentItems) {
            if (starter.add(i)) {
                startingCost += i.cost;
                ++startingSize;
            }
        }
        generateCombinations(starter, 0, startingSize, startingCost);
    }





    /**
     * Main function that will test every build combination and sort them by performance.
     * If isCombo is set to true, abilityTypes refers to the combo.
     * If isCombo is set to false, abilityTypes refers to the ability priorities to calculate dps (for example, q,w,e,r,auto).
     */
    public boolean useAutosBetweenAbilities = false;
    public void sortBuilds(Champion c, Champion e, AbilityType[] abilityTypes, boolean isCombo) {
        if (maxItems < permanentItems.size()) {
            System.out.println("More permanent items than the maximum items allowed!");
            return;
        }
        Inventory inventory = new Inventory();
        if (!inventory.addAll(permanentItems)) {
            System.out.println("There are conflicts with the permanent items!");
            return;
        }
        if (c.getRunes() != null && c.getRunes().size() != 0) System.out.println("Given champion already has runes, keeping those for the test");
        else c.setRunePage(runes);

        generateCombinations();
        if (true) {
            printer.println("Generated " + combinations.size() + " builds.");
        }

        List<BuildScore> buildScores = new ArrayList<>();

        int i = 1;
        int percentAmount = combinations.size()/10;
        if (combinations.size() < 1000) percentAmount = 1002;
        for (Inventory combination : combinations) {
            if (i%percentAmount == 0) System.out.println(i/percentAmount*10 + "% done.");
            ++i;

            combination.sort();
            c.setInventory(combination);

            SimulationManager sm = new SimulationManager();

            sm.setChampion(c);
            sm.setEnemy(e);

            float score;
            if (isCombo) score = sm.simulateCombo(abilityTypes, false);
            else {
                sm.useAutosBetweenAbilities = useAutosBetweenAbilities;
                score = sm.simulateDps(abilityTypes, false);
            }

            BuildScore bs = new BuildScore(score, combination.getItems());
            buildScores.add(bs);
        }

        if (isCombo) buildScores.sort(new BuildScoreComparator(true));
        else {
            if (GlobalVariables.DpsInsteadOfTime) buildScores.sort(new BuildScoreComparator(true));
            else buildScores.sort(new BuildScoreComparator(false));
        }


        int displayedBuilds2 = Math.min(GlobalVariables.displayedBuilds, buildScores.size());
        printer.print("The best " + displayedBuilds2 + " builds, sorted by ");
        if (isCombo) {
            printer.println("damage done with the following combo:");
            printer.printCombo(abilityTypes);
        }
        else {
            if (GlobalVariables.DpsInsteadOfTime) printer.println("DPS (until enemy is killed):");
            else printer.println("DPS (time taken to kill enemy):");
        }
        printer.printScores(buildScores, displayedBuilds2);
    }


    public void setRunePage(RunePage runes) {
        this.runes = new RunePage(runes);
    }
    public void setPermanentItems(List<Item> items) {
        permanentItems = new ArrayList<>();
        permanentItems.addAll(items);
    }
    public void setVariableItems(List<Item> items) {
        variableItems = new ArrayList<>();
        variableItems.addAll(items);
    }
    public void setMaxItems(int x) {
        maxItems = x;
    }
    public void setMaxCost(int x) {
        maxCost = x;
    }

    public BuildTester(int maxItems, int maxCost) {
        this.maxItems = maxItems;
        this.maxCost = maxCost;
    }
}
//change to adding items to inventory while calculating combinations. also add possibility for amount of gold