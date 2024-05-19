package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SynchronizedSouls extends Item {
    public static final String name = "Synchronized Souls";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 900;

    public SynchronizedSouls() {
        super(name, type, cost);
        ms = 90;
    }

    @Override
    public Item makeCopy() {
        return new SynchronizedSouls();
    }
}
