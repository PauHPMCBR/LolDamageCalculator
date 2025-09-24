package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;


public class ProfaneHydra extends Item {
    public static final String name = "Profane Hydra";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2850;

    public ProfaneHydra() {
        super(name, type, cost);
        ad = 55;
        lethality = 18;
        ah = 10;

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
        return new ProfaneHydra();
    }
}
