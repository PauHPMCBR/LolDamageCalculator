package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NavoriQuickblades extends Item {
    public static final String name = "Navori Quickblades";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3400;


    public NavoriQuickblades() {
        super(name, type, cost);
        ad = 60;
        crit = 20;
        ah = 20;
    }

    public void specialStats() {
        cs.navoriPercent = 1 + owner.CRIT_CHANCE/500; //untested
    }
    public void onHit() {
        owner.q.currentCooldown *= (1 - 0.12);
        owner.w.currentCooldown *= (1 - 0.12);
        owner.e.currentCooldown *= (1 - 0.12);
    }
    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new NavoriQuickblades();
    }
}
