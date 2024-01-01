package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Scorch extends Rune {
    public static final String name = "Scorch";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 3;
    public static final int row = 0;

    public Scorch() {
        super(name, path, column, row);
        rune_cooldown = 10;
        System.out.println("a");
    }

    public void extraDmg() {
        System.out.println("b");
        if (canUse()) {
            System.out.println("c");
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 20 + 20f/17*(cs.champion.lvl-1), 2);
        }
    }

    @Override
    public Rune makeCopy() {
        return new Scorch();
    }
}