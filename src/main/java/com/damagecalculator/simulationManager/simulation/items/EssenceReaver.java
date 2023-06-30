package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class EssenceReaver extends Item {
    public static final String name = "Essence Reaver";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2900;

    public EssenceReaver() {
        super(name, type, cost);
        ad = 55;
        crit = 20;
        ah = 20;
        item_cooldown = 1.5f;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        (float) (1.3*owner.BASE_AD + 0.2*owner.BONUS_AD), 1);
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new EssenceReaver();
    }
}
