package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RanduinsOmen extends Item {
    public static final String name = "Randuin's Omen";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    public RanduinsOmen() {
        super(name, type, cost);
        armor = 75;
        hp = 350;
    }

    @Override
    public Item makeCopy() {
        return new RanduinsOmen();
    }
}
