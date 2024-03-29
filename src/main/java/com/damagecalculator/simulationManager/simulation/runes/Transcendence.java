package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Transcendence extends Rune {
    public static final String name = "Transcendence";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 2;
    public static final int row = 0;

    public Transcendence() {
        super(name, path, column, row);
    }

    public void specialStats() {
        if (cs.champion.lvl > 4) cs.champion.AH += 5;
        if (cs.champion.lvl > 7) cs.champion.AH += 5; //ignoring takedown
    }

    @Override
    public Rune makeCopy() {
        return new Transcendence();
    }
}
