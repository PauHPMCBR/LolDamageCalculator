package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class BountyOfWorlds extends Item {
    public static final String name = "Bounty of Worlds";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //upgraded from runic atlas

    public BountyOfWorlds() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;
    }

    @Override
    public Item makeCopy() {
        return new BountyOfWorlds();
    }
}
