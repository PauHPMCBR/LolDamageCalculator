package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Heartsteel extends Item {
    public static final String name = "Heartsteel";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public Heartsteel() {
        super(name, type, cost);
        hp = 900;
        hp_regen = 100;
        item_cooldown = 30;
    }

    //supposing it's already charged
    public void onHit() {
        if (canUse()) {
            putOnCooldown();
            float dmg = 70f + 0.06f * owner.getMaxHP();
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
            owner.BONUS_HP += dmg * 0.1f;
        }
    }

    @Override
    public Item makeCopy() {
        return new Heartsteel();
    }
}
