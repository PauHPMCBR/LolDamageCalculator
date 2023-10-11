package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class SummonAery extends Rune {
    public static final String name = "Summon Aery";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 0;
    public static final int row = 0;

    public SummonAery() {
        super(name, path, column, row);
        rune_cooldown = 4; //aprox
    }

    public void extraDmg() {
        if (canUse()) {
            float dmg = 10 + 40f / 17f * (owner.lvl - 1);
            dmg += 0.1f * owner.BONUS_AD + 0.05f * owner.AP;
            damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg, 2);
            putOnCooldown();
        }
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Rune makeCopy() {
        return new SummonAery();
    }
}