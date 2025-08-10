package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class PhantomDancer extends Item {
    public static final String name = "Phantom Dancer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2650;

    public PhantomDancer() {
        super(name, type, cost);
        as = 65;
        crit = 25;
        percent_ms = 10;
    }

    @Override
    public Item makeCopy() {
        return new PhantomDancer();
    }
}
