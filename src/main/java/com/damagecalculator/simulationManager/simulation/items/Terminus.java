package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Terminus extends Item {
    public static final String name = "Terminus";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3200;

    boolean wasLastLight;
    int lightStacks, darkStacks;

    public Terminus() {
        super(name, type, cost);
        ad = 40;
        as = 30;
    }

    public void specialStats() {
        lightStacks = 0;
        darkStacks = 0;
        wasLastLight = false;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 30, 1);
        if (wasLastLight && darkStacks < 5) {
            owner.increaseArmorPen(-darkStacks * 6); //TODO check if this works fine
            ++darkStacks;
            owner.increaseArmorPen(darkStacks * 6);
        }
        else if (lightStacks < 5) {
            ++lightStacks;
            if (owner.is_ranged) {
                owner.ARMOR += 3;
                owner.MAGIC_RESIST += 3;
            }
            else {
                owner.ARMOR += 5;
                owner.MAGIC_RESIST += 5;
            }
        }
        wasLastLight = !wasLastLight;
    }

    @Override
    public Item makeCopy() {
        return new Terminus();
    }
}
