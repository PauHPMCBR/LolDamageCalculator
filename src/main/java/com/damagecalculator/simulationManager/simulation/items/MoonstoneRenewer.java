package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MoonstoneRenewer extends Item {
    public static final String name = "Moonstone Renewer";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2500;

    public MoonstoneRenewer() {
        super(name, type, cost);
        ap = 40;
        ah = 20;
        hp = 200;
        manaRegen = 100;
    }

    //ignoring healing passive and mythic passive

    @Override
    public Item makeCopy() {
        return new MoonstoneRenewer();
    }
}
