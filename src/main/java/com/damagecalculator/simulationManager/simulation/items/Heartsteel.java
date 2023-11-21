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
        hp = 800;
        hp_regen = 200;
        item_cooldown = 30;
    }

    //supposing it's already charged
    public void onHit() {
        if (canUse()) {
            putOnCooldown();
            float dmg = 100f + 0.05f * owner.getMaxHP();
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
            owner.BONUS_HP += 12 + 0.006 * owner.getMaxHP();
        }
    }

    @Override
    public Item makeCopy() {
        return new Heartsteel();
    }
}
