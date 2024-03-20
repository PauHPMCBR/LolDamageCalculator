package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class HearthboundAxe extends Item {
    public static final String name = "Hearthbound Axe";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1150;

    public HearthboundAxe() {
        super(name, type, cost);
        ad = 15;
        as = 25;
    }

    @Override
    public Item makeCopy() {
        return new HearthboundAxe();
    }
}
