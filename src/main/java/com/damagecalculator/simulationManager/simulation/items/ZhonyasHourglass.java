package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ZhonyasHourglass extends Item {
    public static final String name = "Zhonya's Hourglass";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public ZhonyasHourglass() {
        super(name, type, cost);
        ap = 80;
        ah = 15;
        armor = 45;
    }

    @Override
    public Item makeCopy() {
        return new ZhonyasHourglass();
    }
}