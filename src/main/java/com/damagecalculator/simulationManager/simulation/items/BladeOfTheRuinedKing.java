package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BladeOfTheRuinedKing extends Item {
    public static final String name = "Blade of the Ruined King";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    public BladeOfTheRuinedKing() {
        super(name, type, cost);
        ad = 40;
        as = 25;
        lifesteal = 10;
    }

    public void onHit() {
        float mult = 0.1f;
        if (owner.is_ranged) mult = 0.06f;
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, Math.max(15, owner.getEnemy().HP * mult), 1);

    }

    @Override
    public Item makeCopy() {
        return new BladeOfTheRuinedKing();
    }
}
