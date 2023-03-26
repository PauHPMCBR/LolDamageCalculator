package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class Conqueror extends Rune {
    public static final String name = "Conqueror";
    public static final RunePath path = RunePath.Precision;
    public static final int column = 0;
    public static final int row = 3;

    int stacks;

    public Conqueror() {
        super(name, path, column, row);

        stacks = 0;
    }

    void addStack() {
        if (stacks == 12) return;
        ++stacks; //ignoring healing
        if (cs.champion.getAdaptive().equals(DamageType.physicalDmg))
            cs.champion.BONUS_AD += 1.2 + 1.5/17*(cs.champion.lvl-1);
        else
            cs.champion.AP += 2 + 2.5/17*(cs.champion.lvl-1);
    }

    public void onHit() {
        addStack();
        if (!cs.champion.is_ranged) addStack();
    }

    public void extraDmg() {
        addStack();
        addStack();
    }

    @Override
    public Rune makeCopy() {
        return new Conqueror();
    }
}