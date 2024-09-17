package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class StatikkShiv extends Item {
    public static final String name = "Statikk Shiv";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    public StatikkShiv() {
        super(name, type, cost);
        ad = 50;
        as = 40;
        percent_ms = 4;
    }

    @Override
    public Item makeCopy() {
        return new StatikkShiv();
    }
}
