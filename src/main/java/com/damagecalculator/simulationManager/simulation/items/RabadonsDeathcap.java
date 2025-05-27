package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RabadonsDeathcap extends Item {
    public static final String name = "Rabadon's Deathcap";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3500;

    public RabadonsDeathcap() {
        super(name, type, cost);
        ap = 130;
    }

    public void specialStats() {
        owner.AP *= 1.3f; //have to check if some extra ap is skipped
    }

    @Override
    public Item makeCopy() {
        return new RabadonsDeathcap();
    }
}
