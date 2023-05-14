package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ChempunkChainsword extends Item {
    public static final String name = "Chempunk Chainsword";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public ChempunkChainsword() {
        super(name, type, cost);
        ad = 55;
        ah = 25;
        hp = 250;
    }

    @Override
    public Item makeCopy() {
        return new ChempunkChainsword();
    }
}
