package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Dawncore extends Item {
    public static final String name = "Dawncore";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2700;

    public Dawncore() {
        super(name, type, cost);
        ap = 60;
        hsp = 16;
        mana_regen = 100;
    }

    public void specialStats() {
        //ignoring h&sp, summoner spell haste
        owner.AP += 10 * owner.MANA_REGEN/100;
    }

    @Override
    public Item makeCopy() {
        return new Dawncore();
    }
}
