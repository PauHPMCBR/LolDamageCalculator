package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TheBrutalizer extends Item {
    public static final String name = "The Brutalizer";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1337;

    public TheBrutalizer() {
        super(name, type, cost);
        ad = 25;
        ah = 10;
        lethality = 8;
    }

    @Override
    public Item makeCopy() {
        return new TheBrutalizer();
    }
}
