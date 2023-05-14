package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ImmortalShieldbow extends Item {
    public static final String name = "Immortal Shieldbow";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public ImmortalShieldbow() {
        super(name, type, cost);
        ad = 50;
        crit = 20;
        lifesteal = 7;
        item_cooldown = 90; //although unused for now
    }

    //ignoring lifeline

    @Override
    public Item makeCopy() {
        return new ImmortalShieldbow();
    }
}
