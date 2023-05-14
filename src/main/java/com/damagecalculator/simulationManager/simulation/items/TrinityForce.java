package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TrinityForce extends Item {
    public static final String name = "Trinity Force";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3333;

    float originalBaseAd;
    int threefoldStrikeStacks;

    public TrinityForce() {
        super(name, type, cost);
        ad = 40;
        as = 35;
        ah = 20;
        hp = 300;
        item_cooldown = 1.5f;

        threefoldStrikeStacks = 0;
    }

    public void specialStats() {
        originalBaseAd = owner.BASE_AD;
    }

    public void onHit() {
        if (threefoldStrikeStacks < 5) {
            owner.BASE_AD += originalBaseAd * 0.04;
            ++threefoldStrikeStacks;
        }
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 2 * owner.BASE_AD, 1);
            }
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_AD += 3 * owner.legendary_items_carried;
        owner.AH += 3 * owner.legendary_items_carried;
        //ignoring +3 bonus ms
    }

    @Override
    public Item makeCopy() {
        return new TrinityForce();
    }
}
