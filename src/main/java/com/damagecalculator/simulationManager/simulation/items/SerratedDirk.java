package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SerratedDirk extends Item {
    public static final String name = "Serrated Dirk";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public SerratedDirk() {
        super(name, type, cost);
        ad = 30;
        lethality = 10;
    }

    @Override
    public Item makeCopy() {
        return new SerratedDirk();
    }
}
