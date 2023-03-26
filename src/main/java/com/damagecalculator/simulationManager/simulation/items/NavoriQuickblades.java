package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NavoriQuickblades extends Item {
    public static final String name = "Navori Quickblades";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3400;

    boolean isActive;

    public NavoriQuickblades() {
        super(name, type, cost);
        ad = 60;
        crit = 20;
        ah = 20;

        isActive = false;
    }

    public void specialStats() {
        if (owner.CRIT_CHANCE >= 40) isActive = true;
        cs.navoriPercent = 1 + owner.CRIT_CHANCE/500; //untested
    }
    public void onHit() {
        if (isActive) {
            owner.Q.currentCooldown *= (1 - 0.15);
            owner.W.currentCooldown *= (1 - 0.15);
            owner.E.currentCooldown *= (1 - 0.15);
        }
    }

    @Override
    public Item makeCopy() {
        return new NavoriQuickblades();
    }
}
