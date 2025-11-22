package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Ahri extends Champion {
    public static final String name = "Ahri";

    public Ahri() {
        super (name,
                590f,
                104f,
                418f,
                25f,
                21f,
                4.7f,
                53f,
                3f,
                30f,
                1.3f,
                1.75f,
                0.668f,
                3f,
                20f,
                0.625f,
                2.2f,
                true
        );

        passive = new Ability(PASSIVE);

        q = new Ability(Q) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);
                damageDealt += cs.damage.applyDamage(DamageType.trueDmg, dmg, 6);

                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.magicDmg;
        q.cooldown = new float[]{7,7,7,7,7};
        q.damage = new float[]{40,65,90,115,140};
        q.ap_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        q.cast_time = 0.25f;

        w = new Ability(W) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg*0.4f, 3);
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg*0.4f, 3);

                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{9,8,7,6,5};
        w.damage = new float[]{40,60,80,100,120};
        w.ap_scale = new float[]{0.4f,0.4f,0.4f,0.4f,0.4f};
        w.cast_time = 0;

        e = new Ability(E) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP), 6);
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{12,12,12,12,12};
        e.damage = new float[]{80,120,160,200,240};
        e.ap_scale = new float[]{0.85f,0.85f,0.85f,0.85f,0.85f};
        e.cast_time = 0.25f;

        r = new Ability(R) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                int dashes = 3;
                for (int i = 0; i < dashes; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);

                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{140,120,100};
        r.damage = new float[]{75,125,175};
        r.ap_scale = new float[]{0.35f,0.35f,0.35f,0.35f,0.35f};
        r.cast_time = 0;

        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {E, W, Q, R};
    }

    @Override
    public Champion makeCopy() {
        return new Ahri();
    }
}
