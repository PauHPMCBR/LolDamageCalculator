package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class CheapShot extends Rune {
    public static final String name = "Cheap Shot";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 1;
    public static final int row = 0;

    public CheapShot() {
        super(name, path, column, row);
        rune_cooldown = 4;
    }

    public void extraDmg() { //extremely inaccurate, would be MUCH lower in a real game. this supposes perma CC
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.trueDmg, 10 + 35f / 17f * (owner.lvl - 1), 1);
        }
    }

    @Override
    public Rune makeCopy() {
        return new CheapShot();
    }
}