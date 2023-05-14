package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Empty extends Item {
    public static final String name = "Empty";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 0;

    public Empty() {
        super(name, type, cost);
    }

    @Override
    public Item makeCopy() {
        return new Empty();
    }
}
