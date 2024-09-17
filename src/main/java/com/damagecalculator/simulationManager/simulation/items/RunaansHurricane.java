package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RunaansHurricane extends Item {
    public static final String name = "Runaan's Hurricane";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2650;

    public RunaansHurricane() {
        super(name, type, cost);
        as = 40;
        crit = 25;
        percent_ms = 4;
    }

    @Override
    public Item makeCopy() {
        return new RunaansHurricane();
    }
}
