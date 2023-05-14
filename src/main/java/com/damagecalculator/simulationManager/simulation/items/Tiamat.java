package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Tiamat extends Item {
    public static final String name = "Tiamat";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1200;

    public Tiamat() {
        super(name, type, cost);
        ad = 25;
    }

    @Override
    public Item makeCopy() {
        return new Tiamat();
    }
}
