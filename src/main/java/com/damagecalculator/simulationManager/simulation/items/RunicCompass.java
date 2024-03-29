package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RunicCompass extends Item {
    public static final String name = "Runic Compass";
    public static final ItemType type = ItemType.EPIC;
    public static final int cost = 400; //upgraded from runic atlas

    public RunicCompass() {
        super(name, type, cost);
        hp = 100;
        mana_regen = 50;
        hp_regen = 50;
    }

    @Override
    public Item makeCopy() {
        return new RunicCompass();
    }
}
