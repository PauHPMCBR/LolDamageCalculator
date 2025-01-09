package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class AxiomArcanist extends Rune {
    public static final String name = "Axiom Arcanist";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 1;
    public static final int row = 0;

    public AxiomArcanist() {
        super(name, path, column, row);
    }

    public void specialStats() {
        cs.axiomArcanistRune = this;
    }

    @Override
    public Rune makeCopy() {
        return new AxiomArcanist();
    }
}