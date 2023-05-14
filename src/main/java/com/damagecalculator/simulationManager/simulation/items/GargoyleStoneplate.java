package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GargoyleStoneplate extends Item {
    public static final String name = "Gargoyle Stoneplate";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    public GargoyleStoneplate() {
        super(name, type, cost);
        ah = 15;
        armor = 60;
        mr = 60;
    }

    @Override
    public Item makeCopy() {
        return new GargoyleStoneplate();
    }
}
