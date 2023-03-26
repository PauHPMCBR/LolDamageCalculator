package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Celerity extends Rune {
    public static final String name = "Celerity";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 2;
    public static final int row = 1;

    public Celerity() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new Celerity();
    }
}