
package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class FleetFootwork extends Rune {
    public static final String name = "Fleet Footwork";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 0;
    public static final int row = 2;

    public FleetFootwork() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new FleetFootwork();
    }
}