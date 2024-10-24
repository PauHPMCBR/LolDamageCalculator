package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BansheesVeil extends Item {
    public static final String name = "Banshee's Veil";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public BansheesVeil() {
        super(name, type, cost);
        ap = 105;
        mr = 40;
    }

    @Override
    public Item makeCopy() {
        return new BansheesVeil();
    }
}
