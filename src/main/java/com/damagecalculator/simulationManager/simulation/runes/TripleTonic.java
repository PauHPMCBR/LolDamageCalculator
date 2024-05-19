package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class TripleTonic extends Rune {
    public static final String name = "Triple Tonic";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 2;
    public static final int row = 0;

    public TripleTonic() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new TripleTonic();
    }
}