package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZekesConvergence extends Item {
    public static final String name = "Zeke's Convergence";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2400;

    public ZekesConvergence() {
        super(name, type, cost);
        ah = 20;
        hp = 250;
        mana = 250;
        armor = 35;
    }

    @Override
    public Item makeCopy() {
        return new ZekesConvergence();
    }
}
