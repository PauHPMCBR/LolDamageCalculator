package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Redemption extends Item {
    public static final String name = "Redemption";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    public Redemption() {
        super(name, type, cost);
        ah = 15;
        hp = 200;
        manaRegen = 100;
        healShieldPower = 16;
    }

    @Override
    public Item makeCopy() {
        return new Redemption();
    }
}
