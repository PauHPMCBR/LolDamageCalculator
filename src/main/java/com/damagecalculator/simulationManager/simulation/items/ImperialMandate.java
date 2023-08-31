package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ImperialMandate extends Item {
    public static final String name = "Imperial Mandate";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    float dmg;

    public ImperialMandate() {
        super(name, type, cost);
        ap = 55;
        ah = 20;
        mana_regen = 100;
        item_cooldown = 6;
    }

    public void specialStats() {
        dmg = 40 + 2 * Math.max(0, owner.lvl - 8);
    }
    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 1); //supposing every ability slows or cc, not accounting for ally proc
        }
    }

    @Override
    public Item makeCopy() {
        return new ImperialMandate();
    }
}
