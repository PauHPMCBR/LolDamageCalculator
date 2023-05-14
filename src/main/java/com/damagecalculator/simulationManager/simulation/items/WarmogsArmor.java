package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class WarmogsArmor extends Item {
    public static final String name = "Warmog's Armor";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    public WarmogsArmor() {
        super(name, type, cost);
        ah = 10;
        hp = 800;
        hpRegen = 200;
    }

    @Override
    public Item makeCopy() {
        return new WarmogsArmor();
    }
}
