package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class TreasureHunter extends Rune {
    public static final String name = "Treasure Hunter";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 3;
    public static final int row = 0;

    public TreasureHunter() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new TreasureHunter();
    }
}