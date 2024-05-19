package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class TimeWarpTonic extends Rune {
    public static final String name = "Time Warp Tonic";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 2;
    public static final int row = 1;

    public TimeWarpTonic() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new TimeWarpTonic();
    }
}