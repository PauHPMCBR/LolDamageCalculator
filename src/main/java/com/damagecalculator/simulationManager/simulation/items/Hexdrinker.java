package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Hexdrinker extends Item {
    public static final String name = "Hexdrinker";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1300;

    public Hexdrinker() {
        super(name, type, cost);
        ad = 25;
        mr = 25;
    }

    @Override
    public Item makeCopy() {
        return new Hexdrinker();
    }
}
