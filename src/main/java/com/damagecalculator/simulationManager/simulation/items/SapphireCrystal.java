package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SapphireCrystal extends Item {
    public static final String name = "Sapphire Crystal";
    public static final ItemType type = ItemType.BASIC;
    public static final int cost = 300;

    public SapphireCrystal() {
        super(name, type, cost);
        mana = 300;
    }

    @Override
    public Item makeCopy() {
        return new SapphireCrystal();
    }
}
