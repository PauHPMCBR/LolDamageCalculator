package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TheCollector extends Item {
    public static final String name = "The Collector";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3400;

    public TheCollector() {
        super(name, type, cost);
        ad = 60;
        crit = 25;
        lethality = 10;
    }

    public void extraDmg() {
        if (owner.getEnemy().getRelativeMissingHP() > 0.95)
            cs.damage.execute();
    }

    @Override
    public Item makeCopy() {
        return new TheCollector();
    }
}
