package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BulwarkOfTheMountain extends Item {
    public static final String name = "Bulwark of the Mountain";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //

    public BulwarkOfTheMountain() {
        super(name, type, cost);
        ap = 20;
        hp = 250;
        hp_regen = 100;
    }

    @Override
    public Item makeCopy() {
        return new BulwarkOfTheMountain();
    }
}
