package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class AbsoluteFocus extends Rune {
    public static final String name = "Absolute Focus";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 2;
    public static final int row = 2;

    public AbsoluteFocus() {
        super(name, path, column, row);
    }

    public void specialStats() {
        //supposing always above 70% hp
        if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
            cs.champion.BONUS_AD += 1.8f + 16.2f / 17 * (cs.champion.lvl - 1);
        else
            cs.champion.AP += 3 + 27f / 17 * (cs.champion.lvl - 1);
    }

    @Override
    public Rune makeCopy() {
        return new AbsoluteFocus();
    }
}
