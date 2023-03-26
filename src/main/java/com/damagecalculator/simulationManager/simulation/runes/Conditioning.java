package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Conditioning extends Rune {
    public static final String name = "Conditioning";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 2;
    public static final int row = 0;

    public Conditioning() {
        super(name, path, column, row);
    }

    public void specialStats() {
        owner.ARMOR += 8;
        owner.MAGIC_RESIST += 8;
        owner.ARMOR *= 1.03;
        owner.MAGIC_RESIST *= 1.03;
    }

    @Override
    public Rune makeCopy() {
        return new Conditioning();
    }
}