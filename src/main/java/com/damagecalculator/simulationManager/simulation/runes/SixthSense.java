package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class SixthSense extends Rune {
    public static final String name = "Sixth Sense";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 2;
    public static final int row = 0;

    public SixthSense() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new SixthSense();
    }
}