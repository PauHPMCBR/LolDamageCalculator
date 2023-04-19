package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Bloodthirster extends Item {
    public static final String name = "Bloodthirster";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3200;

    public Bloodthirster() {
        super(name, type, cost);
        ad = 55;
        crit = 20;
        lifesteal = 15;
    }

    @Override
    public Item makeCopy() {
        return new Bloodthirster();
    }
}