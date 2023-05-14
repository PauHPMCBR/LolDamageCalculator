package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuinsoosRageblade extends Item {
    public static final String name = "Guinsoo's Rageblade";
    public static final ItemType type = ItemType.MYTHIC;
    public static final int cost = 3200;

    float critOnhit;
    int stacks;
    int autos;

    public GuinsoosRageblade() {
        super(name, type, cost);
        as = 25;
        ad = 30;
        ap = 30;
    }

    public void specialStats() {
        critOnhit = Math.min(owner.CRIT_CHANCE, 100) * 1.5f * (owner.crit_damage / 1.75f);
        owner.CRIT_CHANCE = 0;
        autos = 0;
        stacks = 0;
    }
    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, critOnhit + 30, 1);
        if (stacks == 4) {
            ++autos;
            if (autos == 3) {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, critOnhit + 30, 1); //rageblade onhit again
                for (Item i : owner.allItems) {
                    if (i.name.equals("Guinsoo's Rageblade")) continue;
                    i.onHit();
                }
                autos = 0;
            }
        }
        else {
            ++stacks;
            owner.BONUS_AS += 8;
        }
    }

    public void applyMythicPassive() {
        owner.increaseArmorPen(5 * owner.legendary_items_carried);
        owner.increasePercentageMagicPen(6 * owner.legendary_items_carried);
    }

    @Override
    public Item makeCopy() {
        return new GuinsoosRageblade();
    }
}
