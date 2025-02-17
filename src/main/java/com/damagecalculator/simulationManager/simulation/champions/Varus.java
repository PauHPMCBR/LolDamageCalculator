package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;
import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Varus extends Champion {
    public static final String name = "Varus";

    boolean chargeQ;

    public Varus(int chargeTime) {
        super(
                name,
                600f,
                105f,
                320f,
                40f,
                27f,
                4.6f,
                59f,
                3.4f,
                30f,
                1.3f,
                1.75f,
                0.658f,
                3f,
                17.544f,
                0.658f,
                3.5f,
                true
        );
        this.chargeQ = chargeTime > 0;

        passive = new Ability(AbilityType.PASSIVE) {
            //ignoring the bonus AS
        };

        q = new Ability(Q) { //extraVariable = is W activated
            public void startingCalculations() {
                cast_time = chargeQ ? 1.25f : 0;
            }
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl]*owner.BONUS_AD;
                if (chargeQ) dmg *= 1.5f;

                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 6);
                owner.w.onExpiring();

                if (extraVariable == 1) {
                    float missingHP = ap_scale[lvl];
                    owner.w.damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                            missingHP * owner.getEnemy().getMissingHP(), 3);
                }

                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{16,15,14,13,12};
        q.damage = new float[]{60,106.67f,153.33f,200,246.67f};
        q.ad_scale = new float[]{0.866f,0.933f,1f,1.067f,1.133f};
        q.ap_scale = new float[]{0.06f,0.08f,0.1f,0.12f,0.14f}; //missing hp dmg

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

                float effectMult = 1;
                if (owner.lastAbilityUsed == Q && chargeQ) effectMult = 1.5f;

                float hpMult = 3 + 0.5f * lvl; //from 3 to 5
                hpMult += 0.015f * owner.AP;

                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        hpMult/100 * owner.getEnemy().getMaxHP() * extraVariable * effectMult, 3);

                owner.q.currentCooldown -= owner.q.getCooldown()*(0.13f*extraVariable*effectMult);
                owner.w.currentCooldown -= owner.w.getCooldown()*(0.13f*extraVariable*effectMult);
                owner.e.currentCooldown -= owner.e.getCooldown()*(0.13f*extraVariable*effectMult);

                extraVariable = 0;
            }
            public void onUse() {
                owner.q.extraVariable = 1;
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{40,40,40,40,40};
        w.damage = new float[]{6,12,18,24,30};
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
        e.ad_scale = new float[]{1f,1f,1f,1f,1f};
        e.cast_time = 0.2419f;

        r = new Ability(R) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ap_scale[lvl] * owner.AP, 6);
                owner.w.onCall();
                owner.w.extraVariable = 3;
                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{100,80,60};
        r.damage = new float[]{150,250,350};
        r.ap_scale = new float[]{1,1,1};
        r.cast_time = 0.2419f;

        Item passiveOnHit = new VarusOnhit();
        addUniqueItem(passiveOnHit);

        extraVariableName = "Charge Q";
        upgradeOrder = new AbilityType[] {E, Q, W, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {AUTO, E, Q, R, W};
    }

    @Override
    public Champion makeCopy() {
        return new Varus(chargeQ ? 1 : 0);
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
