package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LifewellPendant extends Item {
    public static final String name = "Lifewell Pendant";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1050;

    public LifewellPendant() {
        super(name, type, cost);
        hp = 150;
        armor = 25;
        ah = 5;
    }

    @Override
    public Item makeCopy() {
        return new LifewellPendant();
    }
}
