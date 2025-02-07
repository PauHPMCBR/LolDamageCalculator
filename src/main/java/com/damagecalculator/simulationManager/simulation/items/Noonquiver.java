package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Noonquiver extends Item {
    public static final String name = "Noonquiver";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1300;

    public Noonquiver() {
        super(name, type, cost);
        ad = 15;
        crit = 20;
    }

    @Override
    public Item makeCopy() {
        return new Noonquiver();
    }
}
