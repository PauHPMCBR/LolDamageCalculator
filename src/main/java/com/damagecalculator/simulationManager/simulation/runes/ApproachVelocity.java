package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class ApproachVelocity extends Rune {
    public static final String name = "Approach Velocity";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 3;
    public static final int row = 1;

    public ApproachVelocity() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new ApproachVelocity();
    }
}