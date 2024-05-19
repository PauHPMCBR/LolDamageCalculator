package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Sheen extends Item {
    public static final String name = "Sheen";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1000;

    public Sheen() {
        super(name, type, cost);
        ah = 10;
        item_cooldown = 1.5f;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD, 1);
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new Sheen();
    }
}
