package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BladeOfTheRuinedKing extends Item {
    public static final String name = "Blade of the Ruined King";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;

    int autos;

    public BladeOfTheRuinedKing() {
        super(name, type, cost);
        ad = 40;
        as = 25;
        lifesteal = 8;
        item_cooldown = 20;

        autos = 0;
    }

    public void onHit() {
        float mult = 0.12f;
        if (owner.is_ranged) mult = 0.09f;
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, Math.max(15, owner.getEnemy().HP * mult), 1);

        if (canUse()) {
            ++autos;
            if (autos == 3) {
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 40 + 63f / 9 * Math.max(0, owner.lvl - 9), 1);
                autos = 0;
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new BladeOfTheRuinedKing();
    }
}
