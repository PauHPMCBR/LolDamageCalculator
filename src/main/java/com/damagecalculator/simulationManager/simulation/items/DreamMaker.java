package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class DreamMaker extends Item {
    public static final String name = "Dream Maker";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //upgraded from bounty of worlds

    //ignoring bubble stuff

    public DreamMaker() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;
    }

    @Override
    public Item makeCopy() {
        return new DreamMaker();
    }
}
