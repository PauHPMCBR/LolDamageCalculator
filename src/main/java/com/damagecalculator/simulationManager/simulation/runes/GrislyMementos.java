package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class GrislyMementos extends Rune {
    public static final String name = "Grisly Mementos";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 2;
    public static final int row = 1;

    public GrislyMementos() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new GrislyMementos();
    }
}
