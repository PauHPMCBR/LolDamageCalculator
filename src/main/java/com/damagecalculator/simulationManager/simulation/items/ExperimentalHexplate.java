package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ExperimentalHexplate extends Item {
    public static final String name = "Experimental Hexplate";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    boolean isActive;
    float timeActivated;

    public ExperimentalHexplate() {
        super(name, type, cost);
        ad = 55;
        as = 20;
        hp = 300;
    }

    public void specialStats() {
        owner.ULTIMATE_HASTE += 30;
        isActive = false;
        item_cooldown = 30;
    }

    public void onHit() { //check expiring
        if (isActive && cs.time - timeActivated >= 8) {
            isActive = false;
            owner.BONUS_AS -= 35;
        }
    }

    public void extraDmg() {
        if (canUse() && owner.lastAbilityUsed == AbilityType.R && !isActive) {
            isActive = true;
            timeActivated = cs.time;
            owner.BONUS_AS += 35;
            putOnCooldown();
        }
    }

    @Override
    public Item makeCopy() {
        return new ExperimentalHexplate();
    }
}
