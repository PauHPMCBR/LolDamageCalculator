package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class AbsorbLife extends Rune {
    public static final String name = "Absorb Life";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 1;
    public static final int row = 0;

    public AbsorbLife() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new AbsorbLife();
    }
}