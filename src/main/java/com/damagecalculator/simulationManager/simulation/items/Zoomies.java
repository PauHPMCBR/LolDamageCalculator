package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Zoomies extends Item {
    public static final String name = "Zoomies";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    int lethalityGained;

    public Zoomies() {
        super(name, type, cost);
        ad = 55;
        lethality = 18;
        percent_ms = 6;
    }

    public void specialStats() {
        lethalityGained = Math.max(7, owner.lvl);
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
        return new Zoomies();
    }
}
