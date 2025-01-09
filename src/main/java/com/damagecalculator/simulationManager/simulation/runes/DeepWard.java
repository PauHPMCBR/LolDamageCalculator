package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class DeepWard extends Rune {
    public static final String name = "Deep Ward";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 2;
    public static final int row = 2;

    public DeepWard() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new DeepWard();
    }
}
