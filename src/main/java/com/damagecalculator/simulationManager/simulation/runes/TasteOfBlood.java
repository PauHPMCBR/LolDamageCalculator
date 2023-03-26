package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class TasteOfBlood extends Rune {
    public static final String name = "Taste of Blood";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 1;
    public static final int row = 1;

    public TasteOfBlood() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new TasteOfBlood();
    }
}