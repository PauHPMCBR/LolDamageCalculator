package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class DarkHarvest extends Rune {
    public static final String name = "Dark Harvest";
    public static final RunePath path = RunePath.Domination;
    public static final int column = 0;
    public static final int row = 2;

    int souls;

    public DarkHarvest(int souls) {
        super(name, path, column, row);
        rune_cooldown = 35;
        extraVariableName = "Dark Harvest Souls";
        this.souls = souls;
    }

    public void extraDmg() {
        if (canUse()) {
            if (owner.getEnemy().getRelativeMissingHP() >= 0.5) {
                float dmg = 20 + 9*souls;
                dmg += 0.1f * owner.BONUS_AD + 0.05f * owner.AP;
                damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg, 2);
                ++souls;
                putOnCooldown();
            }
        }
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Rune makeCopy() {
        return new DarkHarvest(souls);
    }
}