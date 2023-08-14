package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Galeforce extends Item {
    public static final String name = "Galeforce";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3400;

    public Galeforce() {
        super(name, type, cost);
        ad = 50;
        as = 15;
        crit = 20;
        ms = 7;
        item_cooldown = 120;
    }

    public void extraDmg() {
        if (canUse()) {
            float missing = (float) Math.min(0.7, owner.getEnemy().getRelativeMissingHP());
            if (missing < 0.75) return; //this way, the execute multiplier is maxed

            putOnCooldown();
            float amount = 50;
            if (owner.lvl >= 10) amount += 7.41 * (owner.lvl - 9);
            amount += 0.15 * owner.BONUS_AD;
            amount += amount * Math.min(0.75, missing) * 5 / 7.5;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, amount);
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, amount);
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, amount);
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_AD += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new Galeforce();
    }
}
