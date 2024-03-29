package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuardiansBlade extends Item {
    public static final String name = "Guardian's Blade";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 950;

    public GuardiansBlade() {
        super(name, type, cost);
        ad = 30;
        ah = 15;
        hp = 150;
    }

    @Override
    public Item makeCopy() {
        return new GuardiansBlade();
    }
}
