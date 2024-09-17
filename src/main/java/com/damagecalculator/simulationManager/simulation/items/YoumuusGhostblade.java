package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class YoumuusGhostblade extends Item {
    public static final String name = "Youmuu's Ghostblade";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public YoumuusGhostblade() {
        super(name, type, cost);
        ad = 60;
        lethality = 18;
    }

    @Override
    public Item makeCopy() {
        return new YoumuusGhostblade();
    }
}
