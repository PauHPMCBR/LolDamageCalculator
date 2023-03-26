package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Dagger extends Item {
    public static final String name = "Dagger";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 300;

    public Dagger() {
        super(name, type, cost);
        as = 12;
    }

    @Override
    public Item makeCopy() {
        return new Dagger();
    }
}
