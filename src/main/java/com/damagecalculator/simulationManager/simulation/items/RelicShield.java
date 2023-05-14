package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RelicShield extends Item {
    public static final String name = "Relic Shield";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 400;

    public RelicShield() {
        super(name, type, cost);
        ap = 5;
        hp = 30;
        hpRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new RelicShield();
    }
}
