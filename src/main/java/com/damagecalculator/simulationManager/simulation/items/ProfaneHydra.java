package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

import static com.damagecalculator.GlobalVariables.waitForExtraDamage;

public class ProfaneHydra extends Item {
    public static final String name = "Profane Hydra";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3400;

    public ProfaneHydra() {
        super(name, type, cost);
        ad = 60;
        lethality = 18;
        ah = 20;

        item_cooldown = 10;
    }

    public void extraDmg() {
        if (canUse()) {
            if (waitForExtraDamage && owner.getEnemy().getRelativeMissingHP() < 0.7) return;

            if (owner.getEnemy().getRelativeMissingHP() >= 0.7)
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.975f * owner.getAD(), 2);
            else
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.65f * owner.getAD(), 2);

            putOnCooldown();
        }
    }

    @Override
    public Item makeCopy() {
        return new ProfaneHydra();
    }
}
