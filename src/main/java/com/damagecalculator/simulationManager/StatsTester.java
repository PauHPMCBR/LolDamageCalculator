package com.damagecalculator.simulationManager;

import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.champions.Dummy;

public class StatsTester {
    public enum StatType {
            BonusHP,
            Armor,
            MagicResist
    };
    public static StatType getType(String s) {
        if (s.contains("HP") || s.contains("hp") || s.contains("Hp")) return StatType.BonusHP;
        if (s.contains("armor") || s.contains("Armor") || s.contains("ARMOR")) return StatType.Armor;
        return StatType.MagicResist;
    }

    private Champion champion;
    private Dummy dummy;
    boolean isCombo;
    AbilityType[] comboOrPrio;

    public StatsTester(Champion champion, Dummy dummy, boolean isCombo, AbilityType[] comboOrPrio) { //champion already includes build and runes!
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

    public float[][] testStats(StatType stat1, int start1, int step1, int end1, StatType stat2, int start2, int step2, int end2) {
        float[][] result = new float[(end1 - start1)/step1 +1][(end2 - start2)/step2 +1];

        for (int i = 0; start1 + i * step1 <= end1; ++i) { //<= works?
            updateEnemy(stat1, start1 + i * step1);
            for (int j = 0; start2 + j * step2 <= end2; ++j) {
                updateEnemy(stat2, start2 + j * step2);

                SimulationManager sm = new SimulationManager();
                sm.setChampion(champion);
                sm.setEnemy(dummy);

                if (isCombo) result[i][j] = sm.simulateCombo(comboOrPrio, false);
                else result[i][j] = sm.simulateDps(comboOrPrio, false);
            }
        }

        return result;
    }

    public float[] testStat(StatType stat, int start, int step, int end) {
        float[] result = new float[(end - start)/step + 1];

        for (int i = 0; start + i * step <= end; ++i) {
            updateEnemy(stat, start + i * step);

            SimulationManager sm = new SimulationManager();
            sm.setChampion(champion);
            sm.setEnemy(dummy);

            if (isCombo) result[i] = sm.simulateCombo(comboOrPrio, false);
            else result[i] = sm.simulateDps(comboOrPrio, false);
        }

        return result;
    }
}
