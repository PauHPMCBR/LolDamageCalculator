package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SorcerersShoes extends Item {
    public static final String name = "Sorcerer's Shoes";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 1100;

    public SorcerersShoes() {
        super(name, type, cost);
        ms = 45;
        magic_pen = 12;
    }

    @Override
    public Item makeCopy() {
        return new SorcerersShoes();
    }
}
