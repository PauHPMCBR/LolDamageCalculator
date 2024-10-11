package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class EssenceReaver extends Item {
    public static final String name = "Essence Reaver";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    public EssenceReaver() {
        super(name, type, cost);
        ad = 60;
        crit = 25;
        ah = 15;
    }

    @Override
    public Item makeCopy() {
        return new EssenceReaver();
    }
}
