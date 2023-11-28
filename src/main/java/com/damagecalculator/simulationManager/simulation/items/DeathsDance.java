package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DeathsDance extends Item {
    public static final String name = "Death's Dance";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    public DeathsDance() {
        super(name, type, cost);
        ad = 55;
        armor = 40;
        ah = 15;
    }

    @Override
    public Item makeCopy() {
        return new DeathsDance();
    }
}
