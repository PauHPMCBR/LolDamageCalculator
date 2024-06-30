package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Opportunity extends Item {
    public static final String name = "Opportunity";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    float lethalityGained;

    public Opportunity() {
        super(name, type, cost);
        ad = 55;
        lethality = 18;
        percent_ms = 6;
    }

    public void specialStats() {
        lethalityGained = 5 + Math.max(0, owner.lvl - 7) * 0.5f;
        if (owner.is_ranged) lethalityGained = 3 + Math.max(0, owner.lvl - 7) * 0.3f;
        owner.LETHALITY += lethalityGained;
    }
    public void extraDmg() {
        if (cs.time >= 3 && lethalityGained != 0) {
            owner.LETHALITY -= lethalityGained;
            lethalityGained = 0;
        }
    }
    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new Opportunity();
    }
}
