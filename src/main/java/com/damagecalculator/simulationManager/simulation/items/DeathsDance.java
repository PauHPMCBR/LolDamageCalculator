package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DeathsDance extends Item {
    public static final String name = "Death's Dance";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3300;

    public DeathsDance() {
        super(name, type, cost);
        ad = 65;
        armor = 50;
    }

    @Override
    public Item makeCopy() {
        return new DeathsDance();
    }
}
