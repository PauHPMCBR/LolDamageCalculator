package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class GlacialAugment extends Rune {
    public static final String name = "Glacial Augment";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 0;
    public static final int row = 0;

    public GlacialAugment() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new GlacialAugment();
    }
}