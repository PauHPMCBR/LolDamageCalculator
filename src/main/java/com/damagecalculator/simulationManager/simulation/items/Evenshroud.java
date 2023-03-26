package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Evenshroud extends Item {
    public static final String name = "Evenshroud";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2500;

    public Evenshroud() {
        super(name, type, cost);
        ah = 20;
        hp = 200;
        armor = 30;
        mr = 30;
    }

    public void specialStats() {
        cs.damageMultiplier *= 1.1;
    }

    public void applyMythicPassive() {
        owner.ARMOR += 5 * owner.legendary_items_carried;
        owner.MAGIC_RESIST += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new Evenshroud();
    }
}
