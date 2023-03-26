package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SorcerersShoes extends Item {
    public static final String name = "Sorcerer's Shoes";
    public static final ItemType type = ItemType.boots;
    public static final int cost = 1100;

    public SorcerersShoes() {
        super(name, type, cost);
        magic_pen = 18;
    }

    @Override
    public Item makeCopy() {
        return new SorcerersShoes();
    }
}
