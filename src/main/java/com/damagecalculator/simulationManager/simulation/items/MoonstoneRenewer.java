package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MoonstoneRenewer extends Item {
    public static final String name = "Moonstone Renewer";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 2300;

    public MoonstoneRenewer() {
        super(name, type, cost);
        ap = 35;
        ah = 20;
        hp = 200;
        manaRegen = 100;
    }

    //heal and shield power mythic passive

    @Override
    public Item makeCopy() {
        return new MoonstoneRenewer();
    }
}
