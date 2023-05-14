
package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LostChapter extends Item {
    public static final String name = "Lost Chapter";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 1100;

    public LostChapter() {
        super(name, type, cost);
        ap = 40;
        ah = 10;
        mana = 300;
    }

    @Override
    public Item makeCopy() {
        return new LostChapter();
    }
}
