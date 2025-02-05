package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Karma extends Champion {
    public static final String name = "Karma";

    public int useROnlyForQ;

    @Override
    protected void initializeValues(CurrentState currentState) {
        super.initializeValues(currentState);
        ++r.lvl; // starts with r unlocked, can go up to 4 points
    }


    public Karma(int useROnlyForQ) {
        super(
                name,
                630f,
                109f,
                374f,
                40f,
                28f,
                5f,
                51f,
                3.3f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                3f,
                16.146f,
                0.625f,
                2.3f,
                true
        );

        this.useROnlyForQ = (useROnlyForQ == 0 ? 0 : 1);

        passive = new Ability(PASSIVE);

        q = new Ability(Q) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl]*owner.AP;
                if (r.extraVariable == 1) {
                    dmg += 40 + 60*r.lvl + 0.3f*owner.AP;
                    r.extraVariable = 0;
                    r.currentCooldown = r.getCooldown(); // reset cooldown
                    duration = new float[]{1.5f,1.5f,1.5f,1.5f,1.5f};
                }
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);
                r.currentCooldown -= 4;
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                duration = null;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 40 + 90*r.lvl + 0.5f*owner.AP, 6);
                r.currentCooldown -= 4;
            }
        };
        q.damageType = DamageType.magicDmg;
        q.damage = new float[]{70,120,170,220,270};
        q.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        q.cast_time = 0.25f;
        q.cooldown = new float[]{9,8,7,6,5};

        w = new Ability(W) {
            public void onUse() {
                if (r.extraVariable == 1 && useROnlyForQ == 0) {
                    r.extraVariable = 0; // if mantra can be used for other abilities
                    r.currentCooldown = r.getCooldown(); // reset cooldown
                }
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl]*owner.AP, 6);
                r.currentCooldown -= 4;
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl]*owner.AP, 6);
                r.currentCooldown -= 4;
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{12,12,12,12,12};
        w.damage = new float[]{40,65,90,115,140};
        w.ap_scale = new float[]{0.45f,0.45f,0.45f,0.45f,0.45f};
        w.duration = new float[]{2,2,2,2,2};
        w.cast_time = 0.25f;

        e = new Ability(E) {
            public void onUse() {
                if (r.extraVariable == 1 && useROnlyForQ == 0) {
                    r.extraVariable = 0; // if mantra can be used for other abilities
                    r.currentCooldown = r.getCooldown(); // reset cooldown
                }
                currentCooldown = getCooldown();
            }
        };
        e.damageType = null;
        e.cooldown = new float[]{10,9.5f,9,8.5f,8};
        e.cast_time = 0;

        r = new Ability(R) { // extraVariable = is mantra active (is next ability empowered)
            @Override
            public float getCooldown() {
                if (useROnlyForQ == 1 && q.currentCooldown > 0.05f) return -1;
                return super.getCooldown();
            }
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onUse() {
                extraVariable = 1;
                currentCooldown = getCooldown();
            }
        };
        r.damageType = null;
        r.cooldown = new float[]{40,38,36,34};
        r.cast_time = 0;


        extraVariableName = "Use R only for Q";
        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, E, Q, E, R, E, E, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {R,Q,W,E,AUTO};
    }

    @Override
    public Champion makeCopy() {
        return new Karma(useROnlyForQ);
    }
}
