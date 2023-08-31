package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Zeal extends Item {
    public static final String name = "Zeal";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public Zeal() {
        super(name, type, cost);
        as = 15;
        crit = 15;
        percent_ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new Zeal();
    }
}
