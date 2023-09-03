package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class TearOfTheGoddess extends Item {
    public static final String name = "Tear of the Goddess";
    public static final ItemType type = ItemType.STARTER;
    public static final int cost = 400;

    int manaStacks;

    public TearOfTheGoddess(int manaStacks) {
        super(name, type, cost);
        extraVariableName = "Tear Bonus Mana (0-360)";
        this.manaStacks = Math.min(manaStacks, 360);
        mana = 240 + manaStacks;
    }

    @Override
    public Item makeCopy() {
        return new TearOfTheGoddess(manaStacks);
    }
}
