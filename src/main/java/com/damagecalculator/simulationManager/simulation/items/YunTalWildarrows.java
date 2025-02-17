package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class YunTalWildarrows extends Item {
    public static final String name = "Yun Tal Wildarrows";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int bonusCrit;
    boolean active;
    float attackSpeedBonusStart;

    public YunTalWildarrows(int bonusCrit) {
        super(name, type, cost);
        ad = 55;
        as = 35;
        extraVariableName = "Yun Tal crit (0-25)";
        this.bonusCrit = bonusCrit;
        crit += bonusCrit;
        item_cooldown = 30;
    }

    public void startingCalculations() {
        attackSpeedBonusStart = -1000;
        active = false;
    }

    public void onHit() {
        if (canUse()) {
            putOnCooldown();
            owner.BONUS_AS += 30;
            active = true;
            attackSpeedBonusStart = cs.time;
        }
        if (active && cs.time - attackSpeedBonusStart >= 6) {
            owner.BONUS_AS -= 30;
            active = false;
        }
        //40 second CD, reduced by 1s on hit; 2s for a critical strike
        lastUsed -= 1;
        if (owner.wasLastAutoCrit()) lastUsed -= 1;
    }

    @Override
    public Item makeCopy() {
        return new YunTalWildarrows(bonusCrit);
    }
}
