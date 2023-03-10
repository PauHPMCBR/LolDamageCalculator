package simulationManager;

import java.util.*;
import simulationManager.simulation.*;

/**
 * Class designed to test item combinations and compare them
 */
public class BuildTester {
    private List<Item> runes;

    private int maxItems;
    private List<Item> permanentItems; //items that will be present in every test
    private List<Item> variableItems; //items that can be part of a combination for a build complementing the permanentItems

    private List<List<Item>> combinations;
    private void generateCombinations(List<Item> combination, int start, int size) {
        if (combination.size() == size) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = start; i < variableItems.size(); ++i) {
            combination.add(variableItems.get(i));
            generateCombinations(combination, i + 1, size);
            combination.remove(combination.size() - 1);
        }
    }
    private void generateCombinations(int size) {
        combinations = new ArrayList<>();
        if (size == 0) return;
        generateCombinations(new ArrayList<>(), 0, size);
    }

    /**
     * Class made to sort builds by performance
     */
    static class BuildScore {
        float score;
        List<Item> build;
        public BuildScore(float score, List<Item> build) {
            this.score = score;
            this.build = build;
        }
    }
    private static class BuildScoreComparator implements Comparator<BuildScore> {
        boolean greater;
        public int compare(BuildScore a1, BuildScore a2) {
            if (greater) return (a1.score <= a2.score) ? 1 : -1;
            else return (a1.score >= a2.score) ? 1 : -1;
        }
        public BuildScoreComparator(boolean greater) {
            this.greater = greater;
        }
    }

    public int displayedBuilds = 15;

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
        else c.setRunes(runes);

        generateCombinations(maxItems - permanentItems.size());

        List<BuildScore> buildScores = new ArrayList<>();

        for (List<Item> combination : combinations) {
            Inventory inventory1 = new Inventory(inventory);
            if (!inventory1.addAll(combination)) continue;
            c.setInventory(inventory1);

            SimulationManager sm = new SimulationManager();

            sm.setChampion(c);
            sm.setEnemy(e);

            float score;
            if (isCombo) score = sm.simulateCombo(abilityTypes, false);
            else {
                sm.useAutosBetweenAbilities = useAutosBetweenAbilities;
                score = sm.simulateDps(abilityTypes, false);
            }

            BuildScore bs = new BuildScore(score, inventory1.getItems());
            buildScores.add(bs);
        }

        if (isCombo) buildScores.sort(new BuildScoreComparator(true));
        else buildScores.sort(new BuildScoreComparator(false));


        int displayedBuilds2 = Math.min(displayedBuilds, buildScores.size());
        System.out.print("The best " + displayedBuilds2 + " builds, sorted by ");
        if (isCombo) {
            System.out.println("damage done with the following combo:");
            Printer.printCombo(abilityTypes);
        }
        else System.out.println("DPS (time taken to kill enemy):");
        Printer.printScores(buildScores, displayedBuilds2);
    }


    public void setRunes(List<Item> runes) {
        this.runes = new ArrayList<>();
        this.runes.addAll(runes);
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
}
