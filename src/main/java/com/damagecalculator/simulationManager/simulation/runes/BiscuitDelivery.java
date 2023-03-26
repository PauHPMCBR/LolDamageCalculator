package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class BiscuitDelivery extends Rune {
    public static final String name = "Biscuit Delivery";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 2;
    public static final int row = 2;

    int biscuits;

    public BiscuitDelivery(int biscuits) {
        super(name, path, column, row);
        extraVariableName = "Cookies Consumed";
        this.biscuits = Math.min(biscuits, 3);
    }

    public void specialStats() {
        owner.MANA += 40 * biscuits;
    }

    @Override
    public Rune makeCopy() {
        return new BiscuitDelivery(biscuits);
    }
}
