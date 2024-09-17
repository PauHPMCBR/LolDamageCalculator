package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZhonyasHourglass extends Item {
    public static final String name = "Zhonya's Hourglass";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3250;

    public ZhonyasHourglass() {
        super(name, type, cost);
        ap = 105;
        armor = 50;
    }

    @Override
    public Item makeCopy() {
        return new ZhonyasHourglass();
    }
}
