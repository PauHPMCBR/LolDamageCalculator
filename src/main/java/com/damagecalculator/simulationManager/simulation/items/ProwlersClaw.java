package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ProwlersClaw extends Item {
    public static final String name = "Prowler's Claw";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public ProwlersClaw() {
        super(name, type, cost);
        ad = 55;
        ah = 15;
        lethality = 18;
        item_cooldown = 5;
    }

    public void extraDmg() { //supposing can always use after dash
        if (canUse()) {
            putOnCooldown();
            if (owner.is_ranged)
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 65 + 35 * owner.BONUS_AD, 1);
            else
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 85 + 55 * owner.BONUS_AD, 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new ProwlersClaw();
    }
}
