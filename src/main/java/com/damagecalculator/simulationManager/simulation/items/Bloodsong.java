package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Bloodsong extends Item {
    public static final String name = "Bloodsong";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //upgraded from bounty of worlds

    boolean alreadyApplied;

    public Bloodsong() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;

        item_cooldown = 1.5f;
    }

    public void specialStats() {
        alreadyApplied = false;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD * 1.5f, 1);
                if (!alreadyApplied) {
                    if (owner.is_ranged) cs.damageTrueMultiplier *= 1.06f;
                    else cs.damageTrueMultiplier *= 1.1f;
                    alreadyApplied = true;
                }
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new Bloodsong();
    }
}
