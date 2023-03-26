package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class HextechFlashtraption extends Rune {
    public static final String name = "Hextech Flashtraption";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 1;
    public static final int row = 0;

    public HextechFlashtraption() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new HextechFlashtraption();
    }
}