package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RavenousHydra extends Item {
    public static final String name = "Ravenous Hydra";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3400;

    int ravenousStacks;

    public RavenousHydra(int ravenousStacks) {
        super(name, type, cost);
        ad = 65;
        ah = 25;
        lifesteal = 10;
        extraVariableName = "Ravenous Stacks (0-40)";
        this.ravenousStacks = Math.min(ravenousStacks, 40);
    }

    public void specialStats() {
        owner.BONUS_AD += ravenousStacks*0.5;
    }

    @Override
    public Item makeCopy() {
        return new RavenousHydra(ravenousStacks);
    }
}
