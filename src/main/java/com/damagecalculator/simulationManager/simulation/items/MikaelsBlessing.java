package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MikaelsBlessing extends Item {
    public static final String name = "Mikael's Blessing";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2300;

    public MikaelsBlessing() {
        super(name, type, cost);
        hp = 250;
        manaRegen = 100;
        healShieldPower = 15;
    }

    //ignoring base hp regen for base mana regen

    @Override
    public Item makeCopy() {
        return new MikaelsBlessing();
    }
}
