package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TheCollector extends Item {
    public static final String name = "The Collector";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public TheCollector() {
        super(name, type, cost);
        ad = 55;
        crit = 20;
        lethality = 18;
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
