package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MercurysTreads extends Item {
    public static final String name = "Mercury's Treads";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 1200;

    public MercurysTreads() {
        super(name, type, cost);
        ms = 45;
        mr = 20;
    }

    @Override
    public Item makeCopy() {
        return new MercurysTreads();
    }
}
