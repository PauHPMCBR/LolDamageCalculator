package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Morellonomicon extends Item {
    public static final String name = "Morellonomicon";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2200;

    public Morellonomicon() {
        super(name, type, cost);
        ap = 90;
        ah = 15;
    }

    @Override
    public Item makeCopy() {
        return new Morellonomicon();
    }
}
