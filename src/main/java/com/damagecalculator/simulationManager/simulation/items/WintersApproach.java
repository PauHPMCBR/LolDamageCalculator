package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WintersApproach extends Item {
    public static final String name = "Winter's Approach";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2400;

    int stacks;

    public WintersApproach(int stacks) {
        super(name, type, cost);
        ah = 15;
        hp = 550;
        mana = 500;

        this.stacks = Math.min(stacks, 360);
    }

    public void specialStats() {
        owner.MANA += stacks;
        float baseMana = owner.base_mana + owner.mana_growth * (owner.lvl - 1);
        owner.BONUS_HP += (owner.MANA - baseMana) * 0.15f;
    }

    @Override
    public Item makeCopy() {
        return new WintersApproach(stacks);
    }
}
