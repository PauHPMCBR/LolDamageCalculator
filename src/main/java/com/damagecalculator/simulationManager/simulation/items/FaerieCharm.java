package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class FaerieCharm extends Item {
    public static final String name = "Faerie Charm";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 250;

    public FaerieCharm() {
        super(name, type, cost);
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new FaerieCharm();
    }
}
