package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class EdgeOfNight extends Item {
    public static final String name = "Edge of Night";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public EdgeOfNight() {
        super(name, type, cost);
        ad = 50;
        lethality = 15;
        hp = 250;
    }

    @Override
    public Item makeCopy() {
        return new EdgeOfNight();
    }
}
