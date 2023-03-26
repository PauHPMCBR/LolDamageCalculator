package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class NullifyingOrb extends Rune {
    public static final String name = "Nullifying Orb";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 1;
    public static final int row = 0;

    public NullifyingOrb() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new NullifyingOrb();
    }
}