package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MercurialScimitar extends Item {
    public static final String name = "Mercurial Scimitar";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    public MercurialScimitar() {
        super(name, type, cost);
        ad = 50;
        mr = 35;
        lifesteal = 10;
    }

    @Override
    public Item makeCopy() {
        return new MercurialScimitar();
    }
}
