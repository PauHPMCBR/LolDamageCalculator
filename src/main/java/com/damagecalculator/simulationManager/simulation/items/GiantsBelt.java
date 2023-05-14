package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GiantsBelt extends Item {
    public static final String name = "Giant's Belt";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public GiantsBelt() {
        super(name, type, cost);
        hp = 350;
    }

    @Override
    public Item makeCopy() {
        return new GiantsBelt();
    }
}
