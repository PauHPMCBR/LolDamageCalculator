package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ShurelyasBattlesong extends Item {
    public static final String name = "Shurelya's Battlesong";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 2300;

    public ShurelyasBattlesong() {
        super(name, type, cost);
        ap = 35;
        ah = 20;
        hp = 200;
        mana_regen = 100;
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new ShurelyasBattlesong();
    }
}
