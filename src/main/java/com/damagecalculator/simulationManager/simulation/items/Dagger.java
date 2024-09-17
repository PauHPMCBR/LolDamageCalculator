package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Dagger extends Item {
    public static final String name = "Dagger";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 250;

    public Dagger() {
        super(name, type, cost);
        as = 10;
    }

    @Override
    public Item makeCopy() {
        return new Dagger();
    }
}
