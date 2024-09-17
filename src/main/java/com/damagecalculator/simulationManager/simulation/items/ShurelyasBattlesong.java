package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ShurelyasBattlesong extends Item {
    public static final String name = "Shurelya's Battlesong";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2200;

    public ShurelyasBattlesong() {
        super(name, type, cost);
        ap = 50;
        ah = 15;
        percent_ms = 4;
        mana_regen = 125;
    }

    @Override
    public Item makeCopy() {
        return new ShurelyasBattlesong();
    }
}
