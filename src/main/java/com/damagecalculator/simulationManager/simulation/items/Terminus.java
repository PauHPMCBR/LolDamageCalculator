package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class Terminus extends Item {
    public static final String name = "Terminus";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 3000;

    boolean wasLastLight;
    int lightStacks, darkStacks;

    public Terminus() {
        super(name, type, cost);
        ad = 35;
        as = 35;
    }

    public void specialStats() {
        lightStacks = 0;
        darkStacks = 0;
        wasLastLight = false;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 30, 1);
        if (wasLastLight && darkStacks < 3) {
            owner.decreaseArmorPen(darkStacks * 10);
            ++darkStacks;
            owner.increaseArmorPen(darkStacks * 10);
        }
        else if (lightStacks < 3) {
            ++lightStacks;
            float resistIncrease = 6;
            if (owner.lvl >= 11) ++resistIncrease;
            if (owner.lvl >= 14) ++resistIncrease;

            owner.ARMOR += resistIncrease;
            owner.MAGIC_RESIST += resistIncrease;
        }
        wasLastLight = !wasLastLight;
    }

    @Override
    public Item makeCopy() {
        return new Terminus();
    }
}
