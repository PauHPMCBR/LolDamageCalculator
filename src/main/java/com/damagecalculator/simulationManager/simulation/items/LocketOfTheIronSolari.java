package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class LocketOfTheIronSolari extends Item {
    public static final String name = "Locket of the Iron Solari";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public LocketOfTheIronSolari() {
        super(name, type, cost);
        ah = 20;
        hp = 300;
        armor = 30;
        mr = 30;
        item_cooldown = 90;
    }

    //ignoring shield

    @Override
    public Item makeCopy() {
        return new LocketOfTheIronSolari();
    }
}
