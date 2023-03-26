package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class FuturesMarket extends Rune {
    public static final String name = "Future's Market";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 2;
    public static final int row = 0;

    public FuturesMarket() {
        super(name, path, column, row);
    }

    @Override
    public Rune makeCopy() {
        return new FuturesMarket();
    }
}