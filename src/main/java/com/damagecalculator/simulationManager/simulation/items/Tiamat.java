package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Tiamat extends Item {
    public static final String name = "Tiamat";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1200;

    public Tiamat() {
        super(name, type, cost);
        ad = 20;

        item_cooldown = 10;
    }

    public void extraDmg() {
        if (canUse()) {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.6f * owner.getAD(), 2);
            putOnCooldown();
        }
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new Tiamat();
    }
}
