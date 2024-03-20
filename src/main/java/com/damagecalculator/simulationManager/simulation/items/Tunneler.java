package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Tunneler extends Item {
    public static final String name = "Tunneler";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1150;

    public Tunneler() {
        super(name, type, cost);
        ad = 15;
        hp = 250;
    }

    @Override
    public Item makeCopy() {
        return new Tunneler();
    }
}
