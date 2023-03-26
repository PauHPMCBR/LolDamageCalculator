package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AmplifyingTome extends Item {
    public static final String name = "Amplifying Tome";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 435;

    public AmplifyingTome() {
        super(name, type, cost);
        ap = 20;
    }

    @Override
    public Item makeCopy() {
        return new AmplifyingTome();
    }
}
