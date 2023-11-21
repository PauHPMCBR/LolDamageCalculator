package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SunderedSky extends Item {
    public static final String name = "Sundered Sky";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3100;

    public SunderedSky() {
        super(name, type, cost);
        ad = 55;
        ah = 15;
        hp = 300;

        item_cooldown = 6;
    }

    //TODO it makes it so it DOESNT crit???
    public void onHit() {
        if (canUse()) {
            //will crit for 150% dmg => does 50% dmg? does crit work?
            //ignoring heal part
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.5f * owner.getAD(), 1);
            putOnCooldown();
        }
    }

    @Override
    public Item makeCopy() {
        return new SunderedSky();
    }
}
