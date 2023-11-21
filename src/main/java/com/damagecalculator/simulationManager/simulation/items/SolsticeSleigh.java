package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class SolsticeSleigh extends Item {
    public static final String name = "Solstice Sleigh";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //upgraded from bounty of worlds

    //ignoring hp and ms

    public SolsticeSleigh() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;
    }

    @Override
    public Item makeCopy() {
        return new SolsticeSleigh();
    }
}
