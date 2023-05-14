package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZekesConvergence extends Item {
    public static final String name = "Zeke's Convergence";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2200;

    public ZekesConvergence() {
        super(name, type, cost);
        ah = 15;
        hp = 200;
        mana = 250;
        armor = 45;
    }

    @Override
    public Item makeCopy() {
        return new ZekesConvergence();
    }
}
