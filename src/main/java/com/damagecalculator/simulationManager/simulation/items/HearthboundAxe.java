package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HearthboundAxe extends Item {
    public static final String name = "Hearthbound Axe";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1000;

    public HearthboundAxe() {
        super(name, type, cost);
        ad = 15;
        as = 15;
    }

    @Override
    public Item makeCopy() {
        return new HearthboundAxe();
    }
}
