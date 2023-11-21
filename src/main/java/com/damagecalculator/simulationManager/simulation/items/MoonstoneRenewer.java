package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class MoonstoneRenewer extends Item {
    public static final String name = "Moonstone Renewer";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2200;

    public MoonstoneRenewer() {
        super(name, type, cost);
        ap = 30;
        ah = 20;
        hp = 250;
        mana_regen = 125;
    }

    @Override
    public Item makeCopy() {
        return new MoonstoneRenewer();
    }
}
