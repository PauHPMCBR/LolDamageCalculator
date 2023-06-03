package com.damagecalculator;

import com.damagecalculator.simulationManager.Printer;
import com.damagecalculator.simulationManager.SimulationManager;
import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.Inventory;
import com.damagecalculator.simulationManager.simulation.champions.Dummy;
import com.damagecalculator.simulationManager.simulation.champions.Lucian;
import com.damagecalculator.simulationManager.simulation.items.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

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
        int startingVal = 0, endingVal = 600, interval = 1;

        isCombo = true;
        comboOrPrio = new AbilityType[]{AbilityType.AUTO};

        Inventory withArmorPen = new Inventory();
        withArmorPen.add(new MortalReminder());
        withArmorPen.add(new InfinityEdge());
        withArmorPen.add(new Bloodthirster());

        Inventory withoutArmorPen = new Inventory();
        withoutArmorPen.add(new TheCollector());
        withoutArmorPen.add(new InfinityEdge());
        withoutArmorPen.add(new Bloodthirster());

        Champion lucian = new Lucian();
        lucian.lvl = 10;
        lucian.setInventory(withArmorPen);
        champion = lucian;
        List<List<String>> results = new ArrayList<>(List.of(List.of("armor", "autoDmg")));
        results.addAll(testStat("armor", startingVal, endingVal, interval));
        generateFile(results, "armorPen.csv");


        lucian = new Lucian();
        lucian.lvl = 10;
        lucian.setInventory(withoutArmorPen);
        champion = lucian;
        results = new ArrayList<>(List.of(List.of("armor", "autoDmg")));
        results.addAll(testStat("armor", startingVal, endingVal, interval));
        generateFile(results, "noArmorPen.csv");

        lucian = new Lucian();
        lucian.lvl = 10;
        withoutArmorPen.remove(new TheCollector());
        withoutArmorPen.add(new ChempunkChainsword());
        System.out.println(withoutArmorPen.getItems());
        lucian.setInventory(withoutArmorPen); //85 ad
        champion = lucian;
        results = new ArrayList<>(List.of(List.of("armor", "autoDmg")));
        results.addAll(testStat("armor", startingVal, endingVal, interval));
        generateFile(results, "moreAD.csv");
    }


}
