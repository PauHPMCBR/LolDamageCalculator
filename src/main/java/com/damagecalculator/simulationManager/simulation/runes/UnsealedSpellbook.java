package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class UnsealedSpellbook extends Rune {
    public static final String name = "Unsealed Spellbook";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 0;
    public static final int row = 1;

    public UnsealedSpellbook() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new UnsealedSpellbook();
    }
}