package com.damagecalculator;

import com.damagecalculator.simulationManager.SimulationManager;
import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.Inventory;
import com.damagecalculator.simulationManager.simulation.champions.*;
import com.damagecalculator.simulationManager.simulation.items.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class GraphCreator {

    static String joinStrings(List<String> input) {
        return String.join(",", input);
    }
    static void generateFile(List<List<String>> input, String name) throws IOException {
        File outputFile = new File(name);
        outputFile.createNewFile();
        try (FileWriter fw = new FileWriter(outputFile)) {
            for (List<String> i : input) fw.write(joinStrings(i) + '\n');
        }
        System.out.println("file created?");
    }

    static Champion champion;
    static int[] dummyStats = {1000, 50, 50};
    static boolean isCombo;
    static AbilityType[] comboOrPrio;

    static List<List<String>> testStat(String statName, int startingVal, int endingVal, int interval) {
        List<List<String>> ans = new ArrayList<>();
        for (int i = startingVal; i <= endingVal; i += interval) {
            Dummy dummy = new Dummy(dummyStats[0], dummyStats[1], dummyStats[2]);

            if (statName.equals("armor")) {
                dummy = new Dummy(dummyStats[0], i, dummyStats[2]);
            } else if (statName.equals("mr")) {
                dummy = new Dummy(dummyStats[0], dummyStats[1], i);
            }

            SimulationManager sm = new SimulationManager();
            sm.setChampion(champion);
            sm.setEnemy(dummy);
            if (isCombo) ans.add(List.of(Integer.toString(i), Float.toString(sm.simulateCombo(comboOrPrio, false))));
            else ans.add(List.of(Integer.toString(i), Float.toString(sm.simulateDps(comboOrPrio, false))));

        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        int startingVal = 0, endingVal = 200, interval = 1;

        isCombo = true;
        comboOrPrio = new AbilityType[]{AbilityType.W, AbilityType.W};
        dummyStats[0] = 2000; //3000 hp

        Inventory lud = new Inventory();
        lud.add(new SorcerersShoes());
        lud.add(new StatikkShiv());
        lud.add(new NashorsTooth());
        lud.add(new LudensTempest());

        Inventory lia = new Inventory();
        lia.add(new SorcerersShoes());
        lia.add(new StatikkShiv());
        lia.add(new NashorsTooth());
        lia.add(new LiandrysAnguish());

        Champion kaisa = new Kaisa();
        kaisa.upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        kaisa.lvl = 13;
        kaisa.setInventory(lud);
        champion = kaisa;
        List<List<String>> results = new ArrayList<>(List.of(List.of("mr", "comboDmg")));
        results.addAll(testStat("mr", startingVal, endingVal, interval));
        generateFile(results, "withLudens.csv");


        kaisa = new Kaisa();
        kaisa.upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        kaisa.lvl = 13;
        kaisa.setInventory(lia);
        champion = kaisa;
        results = new ArrayList<>(List.of(List.of("mr", "comboDmg")));
        results.addAll(testStat("mr", startingVal, endingVal, interval));
        generateFile(results, "withLiandry.csv");
    }


}
