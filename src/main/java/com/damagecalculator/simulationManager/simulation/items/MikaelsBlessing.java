package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MikaelsBlessing extends Item {
    public static final String name = "Mikael's Blessing";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    public MikaelsBlessing() {
        super(name, type, cost);
        ah = 15;
        manaRegen = 100;
        mr = 50;
        healShieldPower = 16;
    }

    @Override
    public Item makeCopy() {
        return new MikaelsBlessing();
    }
}
