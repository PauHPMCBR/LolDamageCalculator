package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WatchfulWardstone extends Item {
    public static final String name = "Watchful Wardstone";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public WatchfulWardstone() {
        super(name, type, cost);
        hp = 150;
        armor = 10;
        mr = 15;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new WatchfulWardstone();
    }
}
