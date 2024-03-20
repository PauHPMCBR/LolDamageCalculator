package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class VerdantBarrier extends Item {
    public static final String name = "Verdant Barrier";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1700;

    public VerdantBarrier() {
        super(name, type, cost);
        ap = 40;
        mr = 30;
    }

    @Override
    public Item makeCopy() {
        return new VerdantBarrier();
    }
}
