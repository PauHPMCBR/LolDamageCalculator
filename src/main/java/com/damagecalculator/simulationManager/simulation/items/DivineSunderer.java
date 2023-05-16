package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DivineSunderer extends Item {
    public static final String name = "Divine Sunderer";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3300;

    public DivineSunderer() {
        super(name, type, cost);
        ad = 40;
        ah = 20;
        hp = 300;
        item_cooldown = 1.5f;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                putOnCooldown();
                float amount = (float) (1.6 * owner.BASE_AD);
                amount += owner.getEnemy().getMaxHP() * 0.02;
                if (!owner.is_ranged) amount += owner.getEnemy().getMaxHP() * 0.02;
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, amount, 1);
                //ignoring healing part
            }
        }
    }

    public void applyMythicPassive() {
        owner.increaseArmorPen(3 * owner.legendary_items_carried);
    }

    @Override
    public Item makeCopy() {
        return new DivineSunderer();
    }
}
