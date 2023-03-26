package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BerserkersGreaves extends Item {
    public static final String name = "Berserker's Greaves";
    public static final ItemType type = ItemType.boots;
    public static final int cost = 1100;

    public BerserkersGreaves() {
        super(name, type, cost);
        as = 35;
    }

    @Override
    public Item makeCopy() {
        return new BerserkersGreaves();
    }
}
