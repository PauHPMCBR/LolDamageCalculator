package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class FontOfLife extends Rune {
    public static final String name = "Font of Life";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 1;
    public static final int row = 1;

    public FontOfLife() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new FontOfLife();
    }
}