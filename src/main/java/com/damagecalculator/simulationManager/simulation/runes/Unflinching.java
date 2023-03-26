package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Unflinching extends Rune {
    public static final String name = "Unflinching";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 3;
    public static final int row = 2;

    public Unflinching() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Unflinching();
    }
}