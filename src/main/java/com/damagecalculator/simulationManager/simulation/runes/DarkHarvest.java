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
        rune_cooldown = 45;
        extraVariableName = "Dark Harvest Souls";
        this.souls = souls;
    }

    public void extraDmg() {
        if (canUse()) {
            if (owner.getEnemy().getRelativeMissingHP() >= 0.5) {
                float dmg = 20 + 40f/17f*(owner.lvl -1) + 5*souls;
                dmg += 0.25f * owner.BONUS_AD + 0.15f * owner.AP;
                damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg);
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