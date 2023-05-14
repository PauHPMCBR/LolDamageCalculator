package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class ArchangelsStaff extends Item {
    public static final String name = "Archangel's Staff";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int stacks;

    public ArchangelsStaff(int stacks) {
        super(name, type, cost);
        ap = 70;
        ah = 10;
        hp = 200;
        mana = 500;

        this.stacks = Math.min(stacks, 360);
    }

    public void specialStats() {
        owner.MANA += stacks;
        float baseMana = owner.base_mana + owner.mana_growth * (owner.lvl - 1);
        owner.AP += (owner.MANA - baseMana) * 0.01;
    }

    @Override
    public Item makeCopy() {
        return new ArchangelsStaff(stacks);
    }
}
