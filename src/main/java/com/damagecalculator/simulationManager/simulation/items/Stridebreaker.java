package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Stridebreaker extends Item {
    public static final String name = "Stridebreaker";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public Stridebreaker() {
        super(name, type, cost);
        ad = 40;
        as = 25;
        hp = 400;

        item_cooldown = 15;
    }

    //ignoring ms stuff

    public void extraDmg() {
        if (canUse()) {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.getAD() * 0.8f, 2);
            putOnCooldown();
        }
    }

    @Override
    public Item makeCopy() {
        return new Stridebreaker();
    }
}
