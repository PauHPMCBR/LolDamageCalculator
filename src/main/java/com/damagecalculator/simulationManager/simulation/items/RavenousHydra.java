package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RavenousHydra extends Item {
    public static final String name = "Ravenous Hydra";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;

    public RavenousHydra() {
        super(name, type, cost);
        ad = 65;
        ah = 15;
        lifesteal = 12;

        item_cooldown = 10;
    }

    public void extraDmg() {
        if (canUse()) {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.8f * owner.getAD(), 2);
            putOnCooldown();
        }
    }

    @Override
    public Item makeCopy() {
        return new RavenousHydra();
    }
}
