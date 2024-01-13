package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TitanicHydra extends Item {
    public static final String name = "Titanic Hydra";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;

    public TitanicHydra() {
        super(name, type, cost);
        ad = 50;
        hp = 500;

        item_cooldown = 10;
    }

    public void onHit() {
        if (canUse()) { //in theory the auto reset would be before the big hit, but whatever.
            owner.autoReset();
            if (!owner.is_ranged)
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.06f * owner.getMaxHP(), 1);
            else
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.03f * owner.getMaxHP(), 1);
            putOnCooldown();
            return;
        }
        if (!owner.is_ranged)
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.015f * owner.getMaxHP(), 1);
        else
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.0075f * owner.getMaxHP(), 1);
    }

    @Override
    public Item makeCopy() {
        return new TitanicHydra();
    }
}
