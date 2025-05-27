package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LudensCompanion extends Item {
    public static final String name = "Luden's Companion";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2750;

    public LudensCompanion() {
        super(name, type, cost);
        ap = 100;
        ah = 10;
        mana = 600;

        item_cooldown = 12;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();

            float dmg = 75 + 0.05f * owner.AP;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 2);
            for (int i = 1; i < 6; ++i) { //subsequent stacks apply 1 by 1, big one occurs once
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg * 0.2f, 2); //supposing all hit single-target
            }
        }
    }


    @Override
    public Item makeCopy() {
        return new LudensCompanion();
    }
}
