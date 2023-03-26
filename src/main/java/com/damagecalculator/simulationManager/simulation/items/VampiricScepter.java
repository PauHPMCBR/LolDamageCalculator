package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class VampiricScepter extends Item {
    public static final String name = "Vampiric Scepter";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 900;

    public VampiricScepter() {
        super(name, type, cost);
        ad = 15;
        lifesteal = 7;
    }

    @Override
    public Item makeCopy() {
        return new VampiricScepter();
    }
}
