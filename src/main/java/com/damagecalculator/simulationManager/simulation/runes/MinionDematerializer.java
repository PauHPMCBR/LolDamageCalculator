package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class MinionDematerializer extends Rune {
    public static final String name = "Minion Dematerializer";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 2;
    public static final int row = 1;

    public MinionDematerializer() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new MinionDematerializer();
    }
}