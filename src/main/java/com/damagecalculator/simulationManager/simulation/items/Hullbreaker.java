package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Hullbreaker extends Item {
    public static final String name = "Hullbreaker";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int stacks;
    public Hullbreaker() {
        super(name, type, cost);
        ad = 40;
        hp = 500;
        percent_ms = 4;
    }

    public void specialStats() {
        stacks = 0;
    }

    public void onHit() {
        ++stacks;
        if (stacks == 5) {
            float dmg = 1.2f * owner.BASE_AD;
            dmg += 0.05f * owner.getMaxHP();
            if (owner.is_ranged) dmg = 0.7f * owner.BASE_AD;
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
            stacks = 0;
        }
    }

    @Override
    public Item makeCopy() {
        return new Hullbreaker();
    }
}
