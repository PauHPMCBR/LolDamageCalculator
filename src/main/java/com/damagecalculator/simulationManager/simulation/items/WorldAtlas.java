package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WorldAtlas extends Item {
    public static final String name = "World Atlas";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 400;

    public WorldAtlas() {
        super(name, type, cost);
        hp = 30;
        mana_regen = 25;
        hp_regen = 25;
    }

    @Override
    public Item makeCopy() {
        return new WorldAtlas();
    }
}
