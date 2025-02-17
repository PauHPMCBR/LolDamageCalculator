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
        ad = 40;
        ah = 10;
        hp = 400;

        item_cooldown = 8;
    }

    public void onHit() {
        if (canUse()) {
            //ignoring heal part
            if (!owner.wasLastAutoCrit()) damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.5f * owner.getAD(), 1);
            putOnCooldown();
        }
    }

    @Override
    public Item makeCopy() {
        return new SunderedSky();
    }
}
