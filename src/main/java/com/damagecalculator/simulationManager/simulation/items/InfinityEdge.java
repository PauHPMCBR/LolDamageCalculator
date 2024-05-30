package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class InfinityEdge extends Item {
    public static final String name = "Infinity Edge";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3400;

    public InfinityEdge() {
        super(name, type, cost);
        ad = 80;
        crit = 25;
    }

    public void specialStats() {
        owner.crit_damage += 0.40f;
    }

    @Override
    public Item makeCopy() {
        return new InfinityEdge();
    }
}
