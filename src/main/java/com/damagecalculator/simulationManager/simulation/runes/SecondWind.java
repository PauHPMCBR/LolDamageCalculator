package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class SecondWind extends Rune {
    public static final String name = "Second Wind";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 2;
    public static final int row = 1;

    public SecondWind() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new SecondWind();
    }
}