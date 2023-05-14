package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class AbyssalMask extends Item {
    public static final String name = "Abyssal Mask";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2400;

    public AbyssalMask() {
        super(name, type, cost);
        ah = 10;
        hp = 300;
        mr = 60;
    }

    public void specialStats() {
        float mpen = (float) (5 + 0.012 * (owner.BONUS_HP));
        mpen = Math.min(mpen, 25);
        owner.MAGIC_RESIST += 9; //9 per enemy, supposing 1v1
        owner.getEnemy().MAGIC_RESIST = Math.max(0, owner.getEnemy().MAGIC_RESIST - mpen);
    }

    @Override
    public Item makeCopy() {
        return new AbyssalMask();
    }
}
