package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Bloodthirster extends Item {
    public static final String name = "Bloodthirster";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3400;

    public Bloodthirster() {
        super(name, type, cost);
        ad = 80;
        lifesteal = 18;
    }

    @Override
    public Item makeCopy() {
        return new Bloodthirster();
    }
}
