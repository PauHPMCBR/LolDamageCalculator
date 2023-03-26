package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class GuinsoosRageblade extends Item {
    public static final String name = "Guinsoo's Rageblade";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    int autos;

    public GuinsoosRageblade() {
        super(name, type, cost);
        as = 45;
        crit = 20;

        autos = 0;
    }

    public void specialStats() {
        item_cooldown = owner.CRIT_CHANCE;//item cooldown is amount of crit (from 0 to 100)
        owner.CRIT_CHANCE = 0;
    }
    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, item_cooldown * 2, 1);

        ++autos;
        if (autos == 3) {
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, item_cooldown * 2, 1);
            for (Item i : owner.allItems) {
                if (i.name.equals("Kraken Slayer") || i.name.equals("Guinsoo's Rageblade")) continue;
                i.onHit();
            }
            autos = 0;
        }
    }

    @Override
    public Item makeCopy() {
        return new GuinsoosRageblade();
    }
}
