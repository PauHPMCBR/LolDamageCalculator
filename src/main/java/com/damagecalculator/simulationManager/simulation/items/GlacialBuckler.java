package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GlacialBuckler extends Item {
    public static final String name = "Glacial Buckler";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 900;

    public GlacialBuckler() {
        super(name, type, cost);
        ah = 10;
        armor = 25;
        mana = 300;
    }

    @Override
    public Item makeCopy() {
        return new GlacialBuckler();
    }
}
