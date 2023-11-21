package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NeedlesslyLargeRod extends Item {
    public static final String name = "Needlessly Large Rod";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 1250;

    public NeedlesslyLargeRod() {
        super(name, type, cost);
        ap = 70;
    }

    @Override
    public Item makeCopy() {
        return new NeedlesslyLargeRod();
    }
}
