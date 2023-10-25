package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Varus extends Champion {
    public static final String name = "Varus";

    public Varus() {
        super(
                name,
                600f,
                105f,
                360f,
                40f,
                27f,
                4.6f,
                62f,
                3.4f,
                30f,
                1.3f,
                1.75f,
                0.658f,
                2.5f,
                17.544f,
                1f,
                4f,
                true
        );

        passive = new Ability(AbilityType.PASSIVE) {
            //ignoring the bonus AS
        };

        q = new Ability(Q) { //extraVariable = is W activated
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl]*owner.getAD();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg * 1.5f, 6);
                owner.w.onExpiring();

                if (extraVariable == 1) {
                    float missingHP = 0.06f;
                    if (owner.lvl >= 4) missingHP += 0.02;
                    if (owner.lvl >= 7) missingHP += 0.02;
                    if (owner.lvl >= 10) missingHP += 0.02;
                    if (owner.lvl >= 13) missingHP += 0.02;
                    owner.w.damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                            missingHP * owner.getEnemy().getMissingHP() * 1.5f, 3);
                }

                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{16,15,14,13,12};
        q.damage = new float[]{10,46.67f,83.33f,120,156.67f};
        q.ad_scale = new float[]{0.83f,0.87f,0.9f,0.93f,0.97f};
        q.cast_time = 1.25f; //max cast time, all damage increased by 50%

        w = new Ability(W) { //extraVariable = blight stacks
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onCall() { //= onhit
                if (lvl < 0) return;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                damage[lvl] + ap_scale[lvl] * owner.AP, 3);
                if (extraVariable < 3) ++extraVariable;
            }
            public void onExpiring() { //= use stacks
                if (lvl < 0) return;
                float mult = 3 + 0.5f * lvl; //from 3 to 5
                mult += 0.015 * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        mult/100 * owner.getEnemy().getMaxHP() * extraVariable, 3);

                owner.q.currentCooldown -= owner.q.getCooldown()*(0.12*extraVariable);
                owner.w.currentCooldown -= owner.w.getCooldown()*(0.12*extraVariable);
                owner.e.currentCooldown -= owner.e.getCooldown()*(0.12*extraVariable);

                extraVariable = 0;
            }
            public void onUse() {
                owner.q.extraVariable = 1;
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{40,40,40,40,40};
        w.damage = new float[]{7,13,19,25,31};
        w.ap_scale = new float[]{0.35f,0.35f,0.35f,0.35f,0.35f};
        w.cast_time = 0;

        e = new Ability(E) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD, 6);
                owner.w.onCall();
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.physicalDmg;
        e.cooldown = new float[]{18,16,14,12,10};
        e.damage = new float[]{60,100,140,180,220};
        e.ad_scale = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        e.cast_time = 0.2419f;

        r = new Ability(R) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ap_scale[lvl] * owner.AP, 6);
                owner.w.onCall();
                owner.w.extraVariable = 3;
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{100,80,60};
        r.damage = new float[]{150,250,350};
        r.ap_scale = new float[]{1,1,1};
        r.cast_time = 0.2419f;

        Item passiveOnHit = new VarusOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {E, Q, W, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {AUTO, E, Q, R, W};
    }

    @Override
    public Champion makeCopy() {
        return new Varus();
    }



    public static class VarusOnhit extends Item {
        public static final String name = "_W onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public VarusOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.w.onCall();
        }

        @Override
        public Item makeCopy() {
            return new VarusOnhit();
        }
    }
}
