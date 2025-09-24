package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.Ability;
import com.damagecalculator.simulationManager.simulation.AbilityType;
import com.damagecalculator.simulationManager.simulation.Champion;
import com.damagecalculator.simulationManager.simulation.DamageType;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Azir extends Champion {
    public static final String name = "Azir";

    int currentSoldiers;
    @Override
    public void autoAttack() {
        if (currentSoldiers > 0) {
            w.onCall();
        }
        else {
            boolean isCrit = (autosUsed % 5) < CRIT_CHANCE / 100 * 5; //works for every 20% crit chance, wouldn't work with 15% cloak/zeal, non-random cycle of 5
            if (isCrit) autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD() * crit_damage * 0.86f, 0);
            else autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD(), 0);
        }
        autoCd = 1 / getAttackSpeed();
        ++autosUsed;
        lastAbilityUsed = AUTO;
        if (currentSoldiers > 0) applyOnhit(0.5f, null);
        else applyOnhit(1, null);
    }

    public Azir() {
        super (name,
                575f,
                119f,
                320f,
                40f,
                25f,
                5f,
                56f,
                3.5f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                3f,
                15.625f,
                0.694f,
                5f,
                true
        );

        passive = new Ability(PASSIVE);

        q = new Ability(Q) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,damage[lvl] + ap_scale[lvl]*owner.AP, 6);
                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.magicDmg;
        q.cooldown = new float[]{14,12,10,8,6};
        q.damage = new float[]{60,80,100,120,140};
        q.ap_scale = new float[]{0.35f,0.35f,0.35f,0.35f,0.35f};
        q.cast_time = 0.25f;

        w = new Ability(W) { // extra variable = is first soldier
            public void startingCalculations() {
                currentSoldiers = 0;
                extraVariable = 1;
            }
            public void onUse() {
                ++currentSoldiers;
                if (extraVariable == 1) {
                    extraVariable = 0;
                    currentCooldown = 1.5f;
                }
                else currentCooldown = getCooldown();
            }
            public void onExpiring() {
                --currentSoldiers;
            }
            public void onCall() { // special auto attack. Called when currentSoldiers > 0
                float dmg = 5*Math.max(0, owner.lvl-9);
                dmg += damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg + 0.25f * dmg * (currentSoldiers-1), 6);
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{10,9,8,7,6};
        w.duration = new float[]{10,10,10,10,10};
        w.damage = new float[]{50,65,80,95,110};
        w.ap_scale = new float[]{0.4f,0.45f,0.50f,0.55f,0.60f};
        w.cast_time = 0.25f;

        e = new Ability(E) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl]*owner.AP, 6);
                w.extraVariable = 1;
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{22,20.5f,19,17.5f,16};
        e.damage = new float[]{60,100,140,180,220};
        e.ap_scale = new float[]{0.4f,0.4f,0.4f,0.4f,0.4f};
        e.cast_time = 0;
        r = new Ability(R) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl]*owner.AP, 6);
                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{120,105,90};
        r.damage = new float[]{200,400,600};
        r.ap_scale = new float[]{0.75f,0.75f,0.75f,0.75f,0.75f};
        r.cast_time = 0.5f;

        upgradeOrder = new AbilityType[] {W, Q, E, W, W, R, W, Q, W, Q, R, Q, Q, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {W, AUTO, E, Q, R};
    }

    @Override
    public Champion makeCopy() {
        return new Azir();
    }
}
