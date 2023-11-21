package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TrinityForce extends Item {
    public static final String name = "Trinity Force";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3333;

    public TrinityForce() {
        super(name, type, cost);
        ad = 50;
        as = 33;
        ah = 10;
        hp = 300;
        item_cooldown = 1.5f;
    }
    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 2 * owner.BASE_AD, 1);
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new TrinityForce();
    }
}
