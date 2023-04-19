package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Frostfang extends Item {
    public static final String name = "Frostfang";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 400;

    public Frostfang() {
        super(name, type, cost);
        ap = 15;
        hp = 70;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new Frostfang();
    }
}