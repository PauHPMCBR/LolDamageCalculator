package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SerpentsFang extends Item {
    public static final String name = "Serpent's Fang";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2600;

    public SerpentsFang() {
        super(name, type, cost);
        ad = 55;
        lethality = 12;
    }

    @Override
    public Item makeCopy() {
        return new SerpentsFang();
    }
}
