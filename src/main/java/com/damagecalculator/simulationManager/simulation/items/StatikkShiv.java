package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class StatikkShiv extends Item {
    public static final String name = "Statikk Shiv";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    int remainingAttacks;

    public StatikkShiv() {
        super(name, type, cost);
        ad = 45;
        as = 30;
        percent_ms = 4;
    }

    public void startingCalculations() {
        remainingAttacks = 3;
        item_cooldown = 25;
        if (owner.lvl > 7) item_cooldown -= 3 * (Math.min(12, owner.lvl) - 7);
    }

    public void onHit() {
        if (canUse()) {
            remainingAttacks = 3;
            putOnCooldown();
        }
        if (remainingAttacks > 0) {
            cs.damage.applyDamage(DamageType.magicDmg, 60, 1);
            --remainingAttacks;
        }
    }

    @Override
    public Item makeCopy() {
        return new StatikkShiv();
    }
}
