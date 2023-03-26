package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TargonsBuckler extends Item {
    public static final String name = "Targon's Buckler";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 400;

    public TargonsBuckler() {
        super(name, type, cost);
        ap = 10;
        hp = 100;
        hpRegen = 75;
    }

    @Override
    public Item makeCopy() {
        return new TargonsBuckler();
    }
}
