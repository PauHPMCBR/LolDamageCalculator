package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Eclipse extends Item {
    public static final String name = "Eclipse";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2800;

    public Eclipse() {
        super(name, type, cost);
        ad = 70;
        ah = 15;
        item_cooldown = 6;
    }

    public void extraDmg() {
        //ignoring needs 2 hits to proc, ms and shield granted
        if (canUse()) {
            putOnCooldown();
            if (owner.is_ranged)
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (0.04 * owner.getEnemy().getMaxHP()), 1);
            else
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (0.06 * owner.getEnemy().getMaxHP()), 1);
        }
    }

    @Override
    public Item makeCopy() {
        return new Eclipse();
    }
}
