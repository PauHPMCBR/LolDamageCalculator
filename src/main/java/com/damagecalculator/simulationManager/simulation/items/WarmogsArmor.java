package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WarmogsArmor extends Item {
    public static final String name = "Warmog's Armor";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3300;

    public WarmogsArmor() {
        super(name, type, cost);
        hp = 1000;
        hp_regen = 100;
        percent_ms = 4;
    }

    @Override
    public Item makeCopy() {
        return new WarmogsArmor();
    }
}
