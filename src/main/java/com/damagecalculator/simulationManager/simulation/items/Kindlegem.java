package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Kindlegem extends Item {
    public static final String name = "Kindlegem";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 800;

    public Kindlegem() {
        super(name, type, cost);
        hp = 200;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new Kindlegem();
    }
}
