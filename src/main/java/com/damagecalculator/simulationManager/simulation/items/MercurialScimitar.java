package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MercurialScimitar extends Item {
    public static final String name = "Mercurial Scimitar";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public MercurialScimitar() {
        super(name, type, cost);
        ad = 40;
        crit = 20;
        mr = 40;
    }

    @Override
    public Item makeCopy() {
        return new MercurialScimitar();
    }
}
