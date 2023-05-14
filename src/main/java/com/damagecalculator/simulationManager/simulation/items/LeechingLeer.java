package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LeechingLeer extends Item {
    public static final String name = "Leeching Leer";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1300;

    public LeechingLeer() {
        super(name, type, cost);
        ap = 20;
        hp = 250;
        omnivamp = 5;
    }

    @Override
    public Item makeCopy() {
        return new LeechingLeer();
    }
}
