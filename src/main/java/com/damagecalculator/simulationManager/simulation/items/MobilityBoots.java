package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MobilityBoots extends Item {
    public static final String name = "Mobility Boots";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 1000;

    public MobilityBoots() {
        super(name, type, cost);
        ms = 115;
    }

    @Override
    public Item makeCopy() {
        return new MobilityBoots();
    }
}
