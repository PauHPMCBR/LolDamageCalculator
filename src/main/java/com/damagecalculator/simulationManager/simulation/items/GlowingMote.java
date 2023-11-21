package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GlowingMote extends Item {
    public static final String name = "Glowing Mote";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 250;

    public GlowingMote() {
        super(name, type, cost);
        ah = 5;
    }

    @Override
    public Item makeCopy() {
        return new GlowingMote();
    }
}
