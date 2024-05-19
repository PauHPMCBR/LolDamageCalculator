package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class SuddenImpact extends Rune {
    public static final String name = "Sudden Impact";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 1;
    public static final int row = 2;


    public SuddenImpact() {
        super(name, path, column, row);
        rune_cooldown = 10;
    }

    public void extraDmg() { //extremely inaccurate, supposing every ability is a dash
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.trueDmg, 20 + 60/17f*(owner.lvl-1), 1);
        }
    }

    @Override
    public Rune makeCopy() {
        return new SuddenImpact();
    }
}