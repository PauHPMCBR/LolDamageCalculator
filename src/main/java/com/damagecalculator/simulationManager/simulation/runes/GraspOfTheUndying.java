package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class GraspOfTheUndying extends Rune {
    public static final String name = "Grasp of the Undying";
    public static final RunePath path = RunePath.Resolve;
    public static final int column = 0;
    public static final int row = 0;

    int stacks;
    float lastHit;

    public GraspOfTheUndying(int stacks) {
        super(name, path, column, row);
        extraVariableName = "Grasp Procs";
        this.stacks = stacks;
        lastHit = 0;
    }

    public void specialStats() {
        if (owner.is_ranged) owner.BONUS_HP += 7 * stacks;
        else owner.BONUS_HP += 4 * stacks;
    }

    public void onHit() {
        if (cs.time >= lastHit + 3) {
            lastUsed = cs.time;
            float mult = 0.035f;
            if (owner.is_ranged) mult = 0.021f;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, owner.getMaxHP() * mult, 1);
            //ignoring heal part
            owner.BONUS_HP += 4;
            if (!owner.is_ranged) owner.BONUS_HP += 3;
        }
    }

    @Override
    public Rune makeCopy() {
        return new GraspOfTheUndying(stacks);
    }
}