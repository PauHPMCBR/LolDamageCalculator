package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class FaerieCharm extends Item {
    public static final String name = "Faerie Charm";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 250;

    public FaerieCharm() {
        super(name, type, cost);
        mana_regen = 50;
    }

    @Override
    public Item makeCopy() {
        return new FaerieCharm();
    }
}
