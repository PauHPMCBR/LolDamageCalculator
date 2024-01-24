package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SteelSigil extends Item {
    public static final String name = "Steel Sigil";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public SteelSigil() {
        super(name, type, cost);
        ad = 15;
        armor = 30;
    }

    @Override
    public Item makeCopy() {
        return new SteelSigil();
    }
}
