package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class NavoriQuickblades extends Item {
    public static final String name = "Navori Quickblades";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3400;


    public NavoriQuickblades() {
        super(name, type, cost);
        ad = 65;
        crit = 20;
        ah = 15;
    }

    public void specialStats() {
        cs.navoriPercent = 1 + owner.CRIT_CHANCE/500; //untested
    }
    public void onHit() {
        owner.q.currentCooldown *= (1 - 0.15);
        owner.w.currentCooldown *= (1 - 0.15);
        owner.e.currentCooldown *= (1 - 0.15);
    }
    public void applyMythicPassive() {
        owner.BONUS_AD += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new NavoriQuickblades();
    }
}
