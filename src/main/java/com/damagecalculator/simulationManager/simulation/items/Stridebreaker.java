package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Stridebreaker extends Item {
    public static final String name = "Stridebreaker";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3100;

    public Stridebreaker() {
        super(name, type, cost);
        ad = 50;
        as = 20;
        hp = 400;
    }

    //ignoring ms stuff

    @Override
    public Item makeCopy() {
        return new Stridebreaker();
    }
}
