package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class CosmicInsight extends Rune {
    public static final String name = "Cosmic Insight";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 3;
    public static final int row = 0;

    public CosmicInsight() {
        super(name, path, column, row);
    }

    public void specialStats() {
        cs.champion.ITEM_HASTE += 10; //ignoring sum haste
    }

    @Override
    public Rune makeCopy() {
        return new CosmicInsight();
    }
}