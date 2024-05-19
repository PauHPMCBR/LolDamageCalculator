package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ImperialMandate extends Item {
    public static final String name = "Imperial Mandate";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public ImperialMandate() {
        super(name, type, cost);
        ap = 60;
        ah = 20;
        mana_regen = 125;
        item_cooldown = 9;
    }

    //supposing it procs
    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 0.1f * owner.getEnemy().HP, 1); //supposing every ability slows or cc, not accounting for ally proc
        }
    }

    @Override
    public Item makeCopy() {
        return new ImperialMandate();
    }
}
