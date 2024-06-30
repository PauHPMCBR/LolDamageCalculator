package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Electrocute extends Rune {
    public static final String name = "Electrocute";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 0;
    public static final int row = 0;

    int hits;

    public Electrocute() {
        super(name, path, column, row);
        rune_cooldown = 20;
        hits = 0;
    }

    public void extraDmg() {
        if (canUse()) {
            //not exactly correct, but close enough
            ++hits;
            if (hits == 3) {
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(cs.champion.getAdaptive(), (float) (50 + 140/17*(cs.champion.lvl-1)
                        + 0.1*cs.champion.BONUS_AD + 0.05*cs.champion.AP), 2);
                hits = 0;
            }
        }
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Rune makeCopy() {
        return new Electrocute();
    }
}