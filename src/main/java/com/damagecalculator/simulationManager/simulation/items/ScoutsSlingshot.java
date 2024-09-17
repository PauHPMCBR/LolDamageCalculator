package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ScoutsSlingshot extends Item {
    public static final String name = "Scout's Slingshot";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 600;

    public ScoutsSlingshot() {
        super(name, type, cost);
        as = 20;
        item_cooldown = 40;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 40, 1);
        }
    }

    public void onHit() {
        lastUsed -= 1; //reduce item cd
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new ScoutsSlingshot();
    }
}
