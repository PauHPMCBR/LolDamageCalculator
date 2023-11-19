package com.damagecalculator.simulationManager;

import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.champions.Dummy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StatsTester {
    public enum StatType {
            BonusHP,
            Armor,
            MagicResist
    };
    public static class StatParams {
        StatType type;
        int start, step, end;
        public StatParams(StatType type, int start, int step, int end) {
            this.type = type;
            this.start = start;
            this.step = step;
            this.end = end;
        }
        public StatParams(String type, int start, int step, int end) {
            this(getType(type), start, step, end);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof StatParams sp)) return false;
            return sp.type.equals(this.type) && sp.start == this.start && sp.step == this.step && sp.end == this.end;
        }
    }
    public static StatType getType(String s) {
        if (s.contains("HP") || s.contains("hp") || s.contains("Hp")) return StatType.BonusHP;
        if (s.contains("armor") || s.contains("Armor") || s.contains("ARMOR")) return StatType.Armor;
        return StatType.MagicResist;
    }

    private Champion champion;
    private Dummy dummy;
    boolean isCombo;
    AbilityType[] comboOrPrio;

    public StatsTester() {
    }

    public void setSimulation(Champion champion, Dummy dummy, boolean isCombo, AbilityType[] comboOrPrio) { //champion already includes build and runes!
        this.champion = champion;
        this.dummy = dummy;
        this.isCombo = isCombo;
        this.comboOrPrio = comboOrPrio;
    }

    void updateEnemy(StatType statType, int value) {
        switch (statType) {
            case BonusHP -> dummy = new Dummy(dummy.dummyBaseHP, value, dummy.dummyArmor, dummy.dummyMr);
            case Armor -> dummy = new Dummy(dummy.dummyBaseHP, dummy.dummyBonusHP, value, dummy.dummyMr);
            case MagicResist -> dummy = new Dummy(dummy.dummyBaseHP, dummy.dummyBonusHP, dummy.dummyArmor, value);
        }
    }

    public float[][] testStats(StatParams s1, StatParams s2) {
        float[][] result = new float[(s1.end - s1.start)/s1.step +1][(s2.end - s2.start)/s2.step +1];

        for (int i = 0; s1.start + i * s1.step <= s1.end; ++i) { //<= works?
            updateEnemy(s1.type, s1.start + i * s1.step);
            for (int j = 0; s2.start + j * s2.step <= s2.end; ++j) {
                updateEnemy(s2.type, s2.start + j * s2.step);

                SimulationManager sm = new SimulationManager();
                sm.setChampion(champion);
                sm.setEnemy(dummy);

                if (isCombo) result[i][j] = sm.simulateCombo(comboOrPrio, false);
                else result[i][j] = sm.simulateDps(comboOrPrio, false);
            }
        }

        return result;
    }


    StatParams lastStatParams;
    public ArrayList<float[]> results = new ArrayList<>();

    public float[] testStat(StatParams s) {
        float[] result = new float[(s.end - s.start)/s.step + 1];

        for (int i = 0; s.start + i * s.step <= s.end; ++i) {
            updateEnemy(s.type, s.start + i * s.step);

            SimulationManager sm = new SimulationManager();
            sm.setChampion(champion);
            sm.setEnemy(dummy);

            if (isCombo) result[i] = sm.simulateCombo(comboOrPrio, false);
            else result[i] = sm.simulateDps(comboOrPrio, false);
        }

        if (!s.equals(lastStatParams)) {
            lastStatParams = s;
            results.clear();
        }
        results.add(result);
        return result;
    }

    public void saveResults(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        if (results.size() == 0) {
            fw.write('\n');
            fw.close();
            return;
        }
        StringBuilder rowString = new StringBuilder(String.valueOf(lastStatParams.type));
        for (int i = 0; i < results.size(); ++i) rowString.append(",Data").append(i + 1);
        fw.write(rowString + "\n");
        for (int i = 0; i < results.get(0).length; ++i) {
            rowString.setLength(0);
            rowString.append(lastStatParams.start + lastStatParams.step * i);
            for (float[] result : results) rowString.append(",").append(result[i]);
            fw.write(rowString + "\n");
        }
        fw.close();
    }
}
