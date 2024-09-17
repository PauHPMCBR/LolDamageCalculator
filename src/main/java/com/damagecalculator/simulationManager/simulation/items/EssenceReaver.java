package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class EssenceReaver extends Item {
    public static final String name = "Essence Reaver";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3150;

    public EssenceReaver() {
        super(name, type, cost);
        ad = 65;
        crit = 25;
        ah = 20;
    }

    @Override
    public Item makeCopy() {
        return new EssenceReaver();
    }
}
