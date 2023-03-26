package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class ArcaneComet extends Rune {
    public static final String name = "Arcane Comet";
    public static final RunePath path = RunePath.Sorcery;
    public static final int column = 0;
    public static final int row = 1;

    public ArcaneComet() {
        super(name, path, column, row);
    }

    public void specialStats() {
        rune_cooldown = 20 - 12f / 17f * (owner.lvl - 1);
    }

    public void extraDmg() {
        if (canUse()) {
            float dmg = 30 + 70f / 17f * (owner.lvl - 1);
            dmg += 0.35f * owner.BONUS_AD + 0.20f * owner.AP;
            damageDealt += cs.damage.applyDamage(owner.getAdaptive(), dmg);
            putOnCooldown();
        }
        else {
            float remainingCD = lastUsed + rune_cooldown - cs.time;
            lastUsed -= remainingCD * 0.2f; //reduce cd when other abilities are used
        }
    }

    @Override
    public Rune makeCopy() {
        return new ArcaneComet();
    }
}