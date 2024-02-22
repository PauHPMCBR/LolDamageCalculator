package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class LethalTempo extends Rune {
    public static final String name = "Lethal Tempo";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 0;
    public static final int row = 1;

    int autos;

    public LethalTempo() {
        super(name, path, column, row);

        autos = 0;
    }

    public void onHit() {
        if (autos == 6) return;
        ++autos;
        float bonus_as;
        if (cs.champion.is_ranged) bonus_as = 3.3f + 0.3f*cs.champion.lvl;
        else bonus_as = 5 + 2.2f*(int)(Math.min(15, cs.champion.lvl)/3);

        cs.champion.BONUS_AS += bonus_as;
        if (autos == 6) cs.champion.max_as = 10;
    }

    @Override
    public Rune makeCopy() {
        return new LethalTempo();
    }
}