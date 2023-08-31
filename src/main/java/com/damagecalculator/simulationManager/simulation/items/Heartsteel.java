package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Heartsteel extends Item {
    public static final String name = "Heartsteel";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3200;

    public Heartsteel() {
        super(name, type, cost);
        ah = 20;
        hp = 800;
        hp_regen = 200;
        item_cooldown = 30;
    }

    public void onHit() {
        if (canUse()) {
            putOnCooldown();
            float dmg = 125f + 0.06f * owner.getMaxHP();
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
            owner.BONUS_HP += dmg * 0.1;
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_HP *= (1 + 0.01 * owner.legendary_items_carried); //+1% hp for every legendary
    }

    @Override
    public Item makeCopy() {
        return new Heartsteel();
    }
}
