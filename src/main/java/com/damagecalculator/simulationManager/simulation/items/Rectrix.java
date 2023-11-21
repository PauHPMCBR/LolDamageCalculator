package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Rectrix extends Item {
    public static final String name = "Rectrix";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public Rectrix() {
        super(name, type, cost);
        ad = 20;
        percent_ms = 4;
    }

    @Override
    public Item makeCopy() {
        return new Rectrix();
    }
}
