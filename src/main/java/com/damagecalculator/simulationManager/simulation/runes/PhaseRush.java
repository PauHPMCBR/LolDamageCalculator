package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class PhaseRush extends Rune {
    public static final String name = "Phase Rush";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 0;
    public static final int row = 2;

    public PhaseRush() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new PhaseRush();
    }
}