package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class InfinityEdge extends Item {
    public static final String name = "Infinity Edge";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3400;

    public InfinityEdge() {
        super(name, type, cost);
        ad = 70;
        crit = 20;
    }

    public void specialStats() {
        owner.crit_damage += 0.35f;
    }

    public void applyMythicPassive() {
        owner.BONUS_AD += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new InfinityEdge();
    }
}
