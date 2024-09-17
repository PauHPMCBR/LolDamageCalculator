package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NavoriFlickerblade extends Item {
    public static final String name = "Navori Flickerblade";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2650;


    public NavoriFlickerblade() {
        super(name, type, cost);
        as = 40;
        percent_ms = 4;
        crit = 25;
    }

    public void onHit() {
        owner.q.currentCooldown *= (1 - 0.15f);
        owner.w.currentCooldown *= (1 - 0.15f);
        owner.e.currentCooldown *= (1 - 0.15f);
    }

    @Override
    public Item makeCopy() {
        return new NavoriFlickerblade();
    }
}
