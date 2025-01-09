package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Boots extends Item {
    public static final String name = "Boots";
    public static final ItemType type = ItemType.BOOTS;
    public static final int cost = 300;

    public Boots() {
        super(name, type, cost);
        ms = 25;
    }

    //for T2 boots
    public Boots(String name, int cost) {
        super(name, type, cost);
    }

    //TODO boot upgrades

    @Override
    public Item makeCopy() {
        return new Boots();
    }
}
