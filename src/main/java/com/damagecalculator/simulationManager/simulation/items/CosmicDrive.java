package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class CosmicDrive extends Item {
    public static final String name = "Cosmic Drive";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    int instancesOfDamage;

    public CosmicDrive() {
        super(name, type, cost);
        ap = 90;
        ah = 30;
        ms = 5;

        instancesOfDamage = 0;
    }

    public void extraDmg() {
        ++instancesOfDamage;
        if (instancesOfDamage == 3) {
            owner.AP += 40;
        }
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new CosmicDrive();
    }
}
