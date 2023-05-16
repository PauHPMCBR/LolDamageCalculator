package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class RadiantVirtue extends Item {
    public static final String name = "Radiant Virtue";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 2700;

    float bonusHPGained;

    public RadiantVirtue() {
        super(name, type, cost);
        ah = 10;
        hp = 350;
        armor = 30;
        mr = 30;
        item_cooldown = 90;

        bonusHPGained = 0;
    }

    public void extraDmg() { //supposing insta ult, ignoring healing part
        if (canUse()) {
            lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
            bonusHPGained = 0.125f * owner.getMaxHP();
            owner.BONUS_HP += bonusHPGained;
        }
        if (lastUsed < cs.time + 9 && bonusHPGained != 0) { //9s duration, then remove extra hp
            owner.BONUS_HP -= bonusHPGained;
            bonusHPGained = 0;
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_HP += 75 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new RadiantVirtue();
    }
}
