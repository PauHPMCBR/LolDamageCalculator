package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Thornmail extends Item {
    public static final String name = "Thornmail";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    public Thornmail() {
        super(name, type, cost);
        hp = 350;
        armor = 70;
    }

    @Override
    public Item makeCopy() {
        return new Thornmail();
    }
}
