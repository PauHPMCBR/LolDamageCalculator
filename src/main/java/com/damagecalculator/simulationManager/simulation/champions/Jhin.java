package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.Ability;
import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.DamageType;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Jhin extends Champion {
    public static final String name = "Jhin";

    public float getAttackSpeed() {
        return base_as * (1 + 0.03f * lvl);
    }

    float rawBonusAD; // bonus ad if passive didn't exist
    float lastBonusADRegistered;

    void updatePassiveAD() {
        rawBonusAD += BONUS_AD - lastBonusADRegistered;
        float adPercent = 3 + lvl + Math.max(0, lvl - 9) + 2 * Math.max(0, lvl - 11) +
                0.3f * CRIT_CHANCE + 0.25f * BONUS_AS;
        BONUS_AD = rawBonusAD * (1 + adPercent/100) + BASE_AD * (adPercent/100);
        lastBonusADRegistered = BONUS_AD;
    }

    float currentAutoCounter;

    public void autoAttack() {
        updatePassiveAD(); // update percent bonus ad
        ++currentAutoCounter;
        if (currentAutoCounter == 4) {
            float percentMissingHP = 0.15f;
            if (lvl >= 6) percentMissingHP = 0.2f;
            if (lvl >= 11) percentMissingHP = 0.25f;
            autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD() * crit_damage * 0.86f +
                    percentMissingHP * getEnemy().getMissingHP(), 0);
            autoCd = 2.5f;
            currentAutoCounter = 0;
        }
        else {
            boolean isCrit = (autosUsed % 5) < CRIT_CHANCE / 100 * 5; //works for every 20% crit chance, wouldn't work with 15% cloak/zeal, non-random cycle of 5
            if (isCrit) autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD() * crit_damage * 0.86f, 0);
            else autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD(), 0);
            autoCd = 1 / getAttackSpeed();
        }
        ++autosUsed;
        lastAbilityUsed = AUTO;

        applyOnhit(1, null);
    }

    public Jhin() {
        super (name,
                655f,
                107f,
                300f,
                50f,
                24f,
                4.7f,
                59f,
                4.7f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                2.5f,
                15.625f,
                0f,
                0f,
                true
        );

        passive = new Ability(PASSIVE) {
            public void startingCalculations() {
                currentAutoCounter = 0;
                lastBonusADRegistered = 0;
                rawBonusAD = 0;
                //updatePassiveAD();
            }
        };

        q = new Ability(Q) {
            public void onUse() {
                updatePassiveAD();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage[lvl] + ad_scale[lvl] * owner.getAD() + ap_scale[lvl] * owner.AP, 6);

                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{7,6.5f,6,5.5f,5};
        q.damage = new float[]{45,70,95,120,145};
        q.ad_scale = new float[]{0.35f,0.425f,0.5f,0.575f,0.65f};
        q.ap_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        q.cast_time = 0.25f;

        w = new Ability(W) {
            public void onUse() {
                updatePassiveAD();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, damage[lvl] + ad_scale[lvl] * owner.getAD(), 3);

                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.physicalDmg;
        w.cooldown = new float[]{12,12,12,12,12};
        w.damage = new float[]{60,95,130,165,200};
        w.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        w.cast_time = 0;

        e = new Ability(E) {
            public void onUse() {
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                updatePassiveAD();
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ad_scale[lvl] * owner.getAD() + ap_scale[lvl] * owner.AP, 6);
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{24,21.5f,19,16.5f,14};
        e.damage = new float[]{20,80,140,200,260};
        e.ad_scale = new float[]{1.2f,1.2f,1.2f,1.2f,1.2f};
        e.ap_scale = new float[]{1,1,1,1,1};
        e.duration = new float[]{2,2,2,2,2};
        e.cast_time = 0;
        r = new Ability(R) {
            public void onUse() {
                updatePassiveAD();
                float dmg = damage[lvl] + ad_scale[lvl] * owner.getAD();
                int boolets = 3;
                for (int i = 0; i < boolets; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                            dmg * (1 + 3 * owner.getEnemy().getRelativeMissingHP()), 6);

                boolean hasIE = (owner.crit_damage != 1.75f);
                float critMult = 2 + (hasIE ? 0.4f : 0);
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        dmg * (1 + 3 * owner.getEnemy().getRelativeMissingHP()) * critMult, 6);

                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.physicalDmg;
        r.cooldown = new float[]{120,105,90};
        r.damage = new float[]{64,154,244};
        r.ad_scale = new float[]{0.25f,0.25f,0.25f,0.25f,0.25f};
        r.cast_time = 4;

        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {W, AUTO, Q, E};
    }

    @Override
    public Champion makeCopy() {
        return new Jhin();
    }
}
