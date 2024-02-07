package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;
import com.damagecalculator.simulationManager.simulation.Rune;

public class GuinsoosRageblade extends Item {
    public static final String name = "Guinsoo's Rageblade";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    int stacks;
    int autos;

    public GuinsoosRageblade() {
        super(name, type, cost);
        as = 25;
        ad = 35;
        ap = 35;
    }

    public void specialStats() {
        autos = 0;
        stacks = 0;
    }
    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 30, 1);
        if (stacks == 4) {
            ++autos;
            if (autos == 3) {
                /*damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 30, 1); //rageblade onhit again
                //can't call the onhit function cause there are TWO items that should be skipped :/
                for (Item i : owner.allItems) {
                    if (i.name.equals("Guinsoo's Rageblade") || i.name.equals("Kraken Slayer")) continue;
                    i.onHit();
                }
                for (Rune r : owner.getRunes()) r.onHit(); */
                owner.applyOnhit(1, "Guinsoo's Rageblade");
                autos = 0;
            }
        }
        else {
            ++stacks;
            owner.BONUS_AS += 8;
        }
    }

    @Override
    public Item makeCopy() {
        return new GuinsoosRageblade();
    }
}
