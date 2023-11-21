package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CelestialOpposition extends Item {
    public static final String name = "Celestial Opposition";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 400; //upgraded from bounty of worlds

    //ignoring dmg reduc and slow

    public CelestialOpposition() {
        super(name, type, cost);
        hp = 200;
        mana_regen = 75;
        hp_regen = 75;
    }

    @Override
    public Item makeCopy() {
        return new CelestialOpposition();
    }
}
