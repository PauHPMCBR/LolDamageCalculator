package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.w;

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

        Passive = new Ability(AbilityType.passive) {
            //ignoring the bonus AS
        };

        Q = new Ability(AbilityType.q) {
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl]*owner.getAD();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg);
                owner.W.onExpiring();

                if (extraVariable == 1) {
                    float missingHP = 0.06f;
                    if (owner.lvl >= 4) missingHP += 0.02;
                    if (owner.lvl >= 7) missingHP += 0.02;
                    if (owner.lvl >= 10) missingHP += 0.02;
                    if (owner.lvl >= 13) missingHP += 0.02;
                    owner.W.damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                            missingHP * owner.getEnemy().getMissingHP());
                }

                currentCooldown = getCooldown();
            }
        };
        Q.damageType = DamageType.physicalDmg;
        Q.cooldown = new float[]{16,15,14,13,12};
        Q.damage = new float[]{10,46.67f,83.33f,120,156.67f};
        Q.ad_scale = new float[]{0.83f,0.87f,0.9f,0.93f,0.97f};
        Q.cast_time = 0;

        W = new Ability(AbilityType.w) { //extraVariable = blight stacks
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onCall() { //= onhit
                if (lvl < 0) return;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                damage[lvl] + ap_scale[lvl] * owner.AP);
                if (extraVariable < 3) ++extraVariable;
            }
            public void onExpiring() {
                if (lvl < 0) return;
                float mult = 3 + 0.5f * lvl; //from 3 to 5
                mult += 0.025 * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        mult/100 * owner.getEnemy().getMaxHP() * extraVariable);

                owner.Q.currentCooldown -= owner.Q.getCooldown()*(0.12*extraVariable);
                owner.W.currentCooldown -= owner.W.getCooldown()*(0.12*extraVariable);
                owner.E.currentCooldown -= owner.E.getCooldown()*(0.12*extraVariable);

                extraVariable = 0;
            }
            public void onUse() {
                owner.Q.extraVariable = 1;
                currentCooldown = getCooldown();
            }
        };
        W.damageType = DamageType.magicDmg;
        W.cooldown = new float[]{40,40,40,40,40};
        W.damage = new float[]{7,12,17,22,27};
        W.ap_scale = new float[]{0.3f,0.3f,0.3f,0.3f,0.3f};
        W.cast_time = 0;

        E = new Ability(AbilityType.e) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD);
                owner.W.onCall();
                currentCooldown = getCooldown();
            }
        };
        E.cooldown = new float[]{18,16,14,12,10};
        E.damage = new float[]{60,100,140,180,220};
        E.ad_scale = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        E.cast_time = 0.2419f;
        E.damageType = null;

        R = new Ability(AbilityType.r) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ap_scale[lvl] * owner.AP);
                owner.W.onCall();
                owner.W.extraVariable = 3;
            }
        };
        R.cooldown = new float[]{100,80,60};
        R.damage = new float[]{150,250,350};
        R.ap_scale = new float[]{1,1,1};
        R.cast_time = 0.2419f;

        Item passiveOnHit = new VarusOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {e,q,w,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};
        abilityPriorities = new AbilityType[] {auto,e,q,r,w};
    }

    @Override
    public Champion makeCopy() {
        return new Varus();
    }



    public static class VarusOnhit extends Item {
        public static final String name = "_W onhit";
        public static final ItemType type = ItemType.unique;
        public static final int cost = 0;

        public VarusOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.W.onCall();
        }

        @Override
        public Item makeCopy() {
            return new VarusOnhit();
        }
    }
}
