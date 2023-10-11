package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LordDominiksRegards extends Item {
    public static final String name = "Lord Dominik's Regards";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public LordDominiksRegards() {
        super(name, type, cost);
        ad = 35;
        crit = 20;
        armor_pen = 30;
    }

    public void specialStats() {
        float diff = owner.getEnemy().getMaxHP() - owner.getMaxHP();
        diff = Math.min(2500, Math.max(0, diff));
        cs.damageMultiplier *= 1 + diff/10000 * 22f/25f; //should be correct, doesn't amplify true dmg
    }

    @Override
    public Item makeCopy() {
        return new LordDominiksRegards();
    }
}
