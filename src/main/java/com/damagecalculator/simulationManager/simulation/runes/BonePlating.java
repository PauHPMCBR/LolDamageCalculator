package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class BonePlating extends Rune {
    public static final String name = "Bone Plating";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 2;
    public static final int row = 2;

    public BonePlating() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new BonePlating();
    }
}