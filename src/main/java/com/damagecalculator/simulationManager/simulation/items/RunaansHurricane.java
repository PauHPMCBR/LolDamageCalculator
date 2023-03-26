package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RunaansHurricane extends Item {
    public static final String name = "Runaan's Hurricane";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    public RunaansHurricane() {
        super(name, type, cost);
        as = 45;
        crit = 20;
        ms = 7;
    }

    @Override
    public Item makeCopy() {
        return new RunaansHurricane();
    }
}
