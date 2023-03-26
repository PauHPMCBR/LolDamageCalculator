package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class PerfectTiming extends Rune {
    public static final String name = "Perfect Timing";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 1;
    public static final int row = 2;

    public PerfectTiming() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new PerfectTiming();
    }
}