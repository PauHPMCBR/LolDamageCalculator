package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BFSword extends Item {
    public static final String name = "B. F. Sword";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 1300;

    public BFSword() {
        super(name, type, cost);
        ad = 40;
    }

    @Override
    public Item makeCopy() {
        return new BFSword();
    }
}
