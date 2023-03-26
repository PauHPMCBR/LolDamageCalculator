package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class LegendTenacity extends Rune {
    public static final String name = "Legend: Tenacity";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 2;
    public static final int row = 1;

    public LegendTenacity() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new LegendTenacity();
    }
}