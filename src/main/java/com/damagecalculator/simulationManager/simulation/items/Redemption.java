package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Redemption extends Item {
    public static final String name = "Redemption";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public Redemption() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 100;
        ah = 15;
        hsp = 10;
    }

    //ignoring hp regen for mana regen

    @Override
    public Item makeCopy() {
        return new Redemption();
    }
}
