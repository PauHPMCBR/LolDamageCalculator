package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Trailblazer extends Item {
    public static final String name = "Trailblazer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2500;

    public Trailblazer() {
        super(name, type, cost);
        hp = 200;
        armor = 40;
        percent_ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new Trailblazer();
    }
}
