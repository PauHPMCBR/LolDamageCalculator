package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class LegendBloodline extends Rune {
    public static final String name = "Legend: Bloodline";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 2;
    public static final int row = 2;

    public LegendBloodline() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new LegendBloodline();
    }
}