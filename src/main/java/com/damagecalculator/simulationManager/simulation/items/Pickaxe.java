package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Pickaxe extends Item {
    public static final String name = "Pickaxe";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 875;

    public Pickaxe() {
        super(name, type, cost);
        ad = 25;
    }

    @Override
    public Item makeCopy() {
        return new Pickaxe();
    }
}
