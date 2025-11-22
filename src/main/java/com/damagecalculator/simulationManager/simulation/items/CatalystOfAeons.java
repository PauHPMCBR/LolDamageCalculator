package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CatalystOfAeons extends Item {
    public static final String name = "Catalyst of Aeons";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1300;

    public CatalystOfAeons() {
        super(name, type, cost);
        hp = 300;
        mana = 375;
    }

    @Override
    public Item makeCopy() {
        return new CatalystOfAeons();
    }
}
