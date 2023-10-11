package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DoransShield extends Item {
    public static final String name = "Doran's Shield";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 450;

    public DoransShield() {
        super(name, type, cost);
        hp = 110;
    }

    @Override
    public Item makeCopy() {
        return new DoransShield();
    }
}
