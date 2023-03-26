package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Cull extends Item {
    public static final String name = "Cull";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 450;

    public Cull() {
        super(name, type, cost);
        ad = 7;
    }

    @Override
    public Item makeCopy() {
        return new Cull();
    }
}
