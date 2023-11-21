package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Shadowflame extends Item {
    public static final String name = "Shadowflame";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    public Shadowflame() {
        super(name, type, cost);
        ap = 120;
        magic_pen = 12;
    }

    public void specialStats() {
        cs.shadowflameItem = this;
    }

    @Override
    public Item makeCopy() {
        return new Shadowflame();
    }
}
