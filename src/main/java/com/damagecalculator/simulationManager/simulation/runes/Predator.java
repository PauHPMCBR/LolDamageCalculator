package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;
import com.damagecalculator.simulationManager.simulation.items.IonianBootsOfLucidity;

public class Predator extends Rune {
    public static final String name = "Predator";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 0;
    public static final int row = 1;

    boolean hasBoots;

    public Predator() {
        super(name, path, column, row);
    }

    public void specialStats() {
        hasBoots = !owner.getInventory().canAdd(new IonianBootsOfLucidity());
        rune_cooldown = 120 - 60f / 17f * (owner.lvl - 1);
    }

    public void extraDmg() {
        if (canUse()) {
            float dmg = 20 + 160f / 17f * (owner.lvl - 1);
            dmg += owner.BONUS_AD * 0.25 + owner.AP * 0.15;
            damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg);
            putOnCooldown();
        }
    }

    @Override
    public Rune makeCopy() {
        return new Predator();
    }
}