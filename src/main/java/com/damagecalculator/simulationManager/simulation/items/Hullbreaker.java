package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Hullbreaker extends Item {
    public static final String name = "Hullbreaker";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public Hullbreaker() {
        super(name, type, cost);
        ad = 60;
        hp = 400;
        hpRegen = 150;
        ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new Hullbreaker();
    }
}
