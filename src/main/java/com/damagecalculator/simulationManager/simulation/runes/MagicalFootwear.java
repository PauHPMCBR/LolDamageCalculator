package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class MagicalFootwear extends Rune {
    public static final String name = "Magical Footwear";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 1;
    public static final int row = 1;

    public MagicalFootwear() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new MagicalFootwear();
    }
}