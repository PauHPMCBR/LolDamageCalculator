package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ClothArmor extends Item {
    public static final String name = "Cloth Armor";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 300;

    public ClothArmor() {
        super(name, type, cost);
        armor = 15;
    }

    @Override
    public Item makeCopy() {
        return new ClothArmor();
    }
}
