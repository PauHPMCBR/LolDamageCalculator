package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Zephyr extends Item {
    public static final String name = "Zephyr";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 3100;

    public Zephyr() {
        super(name, type, cost);
        ms = 45;
        as = 45;
    }

    @Override
    public Item makeCopy() {
        return new Zephyr();
    }
}
