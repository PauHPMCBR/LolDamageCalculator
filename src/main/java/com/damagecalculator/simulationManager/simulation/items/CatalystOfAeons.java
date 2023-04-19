package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CatalystOfAeons extends Item {
    public static final String name = "Catalyst of Aeons";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1100;

    public CatalystOfAeons() {
        super(name, type, cost);
        hp = 225;
        mana = 300;
    }

    @Override
    public Item makeCopy() {
        return new CatalystOfAeons();
    }
}