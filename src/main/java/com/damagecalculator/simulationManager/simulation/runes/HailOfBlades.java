package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class HailOfBlades extends Rune {
    public static final String name = "Hail of Blades";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 0;
    public static final int row = 3;

    int autos;
    int bonusASGained;
    float prevMaxAs;

    public HailOfBlades() {
        super(name, path, column, row);
        rune_cooldown = 10;

        autos = 0;
    }

    public void specialStats() {
        if (owner.is_ranged) bonusASGained = 80;
        else bonusASGained = 160;
    }

    public void onHit() { //gives as AFTER the 1st auto
        if (canUse()) {
            if (autos == 0) {
                cs.champion.BONUS_AS += bonusASGained;
                prevMaxAs = cs.champion.max_as;
                cs.champion.max_as = 90;
            }
            ++autos;
            if (autos == 3) {
                putOnCooldown();
                cs.champion.max_as = prevMaxAs;
                cs.champion.BONUS_AS -= bonusASGained;
            }
        }
    }

    @Override
    public Rune makeCopy() {
        return new HailOfBlades();
    }
}