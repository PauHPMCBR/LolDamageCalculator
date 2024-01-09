package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NavoriQuickblades extends Item {
    public static final String name = "Navori Quickblades";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;


    public NavoriQuickblades() {
        super(name, type, cost);
        ad = 60;
        crit = 20;
        ah = 15;
    }

    public void specialStats() {
        cs.abilityDamageMultiplier = 1 + owner.CRIT_CHANCE/500; //untested
    }
    public void onHit() {
        owner.q.currentCooldown *= (1 - 0.12f);
        owner.w.currentCooldown *= (1 - 0.12f);
        owner.e.currentCooldown *= (1 - 0.12f);
    }

    @Override
    public Item makeCopy() {
        return new NavoriQuickblades();
    }
}
