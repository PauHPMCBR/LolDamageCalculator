package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RodOfAges extends Item {
    public static final String name = "Rod of Ages";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 2800;

    int stacks;

    public RodOfAges(int stacks) {
        super(name, type, cost);
        ap = 60;
        hp = 400;
        mana = 400;
        extraVariableName = "ROA Minutes (0-10)";
        this.stacks = Math.min(stacks, 10);
    }

    public void specialStats() {
        int bonusHP = 20 * stacks;
        int bonusMana = 20 * stacks;
        int bonusAP = 4 * stacks;
        if (stacks == 10) {
            bonusAP *= 1.5f;
            bonusMana *= 1.5f;
            bonusHP *= 1.5f;
        }
        owner.BONUS_HP += bonusHP;
        owner.AP += bonusAP;
        owner.MANA += bonusMana;
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }


    @Override
    public Item makeCopy() {
        return new RodOfAges(stacks);
    }
}
