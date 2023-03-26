package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CloakOfAgility extends Item {
    public static final String name = "Cloak of Agility";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 600;

    public CloakOfAgility() {
        super(name, type, cost);
        crit = 15;
    }

    @Override
    public Item makeCopy() {
        return new CloakOfAgility();
    }
}
