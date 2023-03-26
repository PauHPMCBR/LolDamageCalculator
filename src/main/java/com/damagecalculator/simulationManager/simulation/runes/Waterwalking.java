package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Waterwalking extends Rune {
    public static final String name = "Waterwalking";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 3;
    public static final int row = 1;

    boolean isOnWater;

    public Waterwalking(boolean isOnWater) {
        super(name, path, column, row);

        this.isOnWater = isOnWater;
    }

    public void specialStats() {
        if (!isOnWater) return;
        if (owner.getAdaptive() == DamageType.physicalDmg)
            owner.BONUS_AD += 3 + 15f / 17f * (owner.lvl - 1);
        else
            owner.AP += 5 + 25f / 17f * (owner.lvl - 1);
    }

    @Override
    public Rune makeCopy() {
        return new Waterwalking(isOnWater);
    }
}