package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class NimbusCloak extends Rune {
    public static final String name = "Nimbus Cloak";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 1;
    public static final int row = 2;

    public NimbusCloak() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new NimbusCloak();
    }
}