package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class LethalTempo extends Rune {
    public static final String name = "Lethal Tempo";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 0;
    public static final int row = 1;

    int stacks;

    public LethalTempo() {
        super(name, path, column, row);
    }

    public void startingCalculations() {
        stacks = 0;
    }

    public void onHit() {
        if (stacks == 6) {
            float dmg = 9 + 21/17f*(owner.lvl-1);
            if (owner.is_ranged) dmg = 6 + 18/17f*(owner.lvl-1);
            damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg * (1 + 0.01f*owner.BONUS_AS), 1);
        }
        else {
            owner.BONUS_AS += owner.is_ranged ? 4 : 6;
            ++stacks;
        }
    }
    @Override
    public Rune makeCopy() {
        return new LethalTempo();
    }
}