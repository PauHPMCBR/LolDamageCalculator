package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class YoumuusGhostblade extends Item {
    public static final String name = "Youmuu's Ghostblade";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public YoumuusGhostblade() {
        super(name, type, cost);
        ad = 55;
        ah = 15;
        lethality = 18;
    }

    @Override
    public Item makeCopy() {
        return new YoumuusGhostblade();
    }
}
