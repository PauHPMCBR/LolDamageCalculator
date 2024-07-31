package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BootsOfSwiftness extends Item {
    public static final String name = "Boots of Swiftness";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 1000;

    public BootsOfSwiftness() {
        super(name, type, cost);
        ms = 60;
    }

    @Override
    public Item makeCopy() {
        return new BootsOfSwiftness();
    }
}
