package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LichBane extends Item {
    public static final String name = "Lich Bane";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public LichBane() {
        super(name, type, cost);
        ap = 85;
        ah = 15;
        percent_ms = 8;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        (float) (0.75 * owner.BASE_AD + 0.5 * owner.AP), 1);
            }
        }
    }

        @Override
    public Item makeCopy() {
        return new LichBane();
    }
}
