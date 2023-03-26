package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Revitalize extends Rune {
    public static final String name = "Revitalize";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 3;
    public static final int row = 1;

    public Revitalize() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Revitalize();
    }
}