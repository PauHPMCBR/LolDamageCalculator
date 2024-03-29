package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class PhantomDancer extends Item {
    public static final String name = "Phantom Dancer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    int autos;

    public PhantomDancer() {
        super(name, type, cost);
        ad = 20;
        as = 30;
        crit = 20;
        percent_ms = 10;
    }

    public void specialStats() {
        autos = 0;
    }

    public void onHit() {
        if (autos < 5) {
            ++autos;
            owner.BONUS_AS += 7;
        }
    }

    @Override
    public Item makeCopy() {
        return new PhantomDancer();
    }
}
