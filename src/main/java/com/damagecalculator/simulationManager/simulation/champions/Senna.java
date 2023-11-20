package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Senna extends Champion {
    public static final String name = "Senna";

    public int souls;

    public Senna(int souls) {
        super (name,
                530f,
                89f,
                350f,
                45f,
                28f,
                4.7f,
                50f,
                0f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                2.5f,
                31.25f,
                0.4f,
                4f,
                true
        );
        this.souls = souls;

        passive = new Ability(PASSIVE) {
            boolean hasHitBefore;

            public void startingCalculations() { //extraVariable are soul stacks
                extraVariable = souls;
                owner.BONUS_AD += 0.75 * extraVariable;
                owner.CRIT_CHANCE = Math.min(100, owner.CRIT_CHANCE + 10 * (extraVariable / 20));
                //ignoring lifesteal and range

                hasHitBefore = false;
            }

            public void onUse() { //soul stacking
                if (currentCooldown > 0) return;
                if (hasHitBefore) {
                    currentCooldown = 6;
                    if (owner.lvl >= 6) currentCooldown = 5;
                    if (owner.lvl >= 11) currentCooldown = 4;

                    ++extraVariable;
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                            owner.getEnemy().HP * 0.01f * Math.min(10, owner.lvl), 3);
                }
                hasHitBefore = !hasHitBefore;
                System.out.println(damageDealt + "xdd");
            }

            public void onCall() { //onHit
                //may be not ability type for navori, needs testing
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BONUS_AD * 0.2f, 3);
                owner.q.currentCooldown -= 1;
                System.out.println(damageDealt);
            }
        };

        q = new Ability(Q) {
            public void startingCalculations() {
                cast_time = owner.getAttackWindupTime() * 0.8f;
            }
            public void onUse() {
                //ignoring heal, slow
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, damage[lvl] + ad_scale[lvl] * owner.BONUS_AD, 6);
                owner.applyOnhit(1, SennaOnhit.name);
                //skip senna onhit, but apply the extra 20% bonus ad onhit either way
                owner.passive.damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BONUS_AD * 0.2f, 3);

                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{15,15,15,15,15};
        q.damage = new float[]{30,60,90,120,150};
        q.ad_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};

        w = new Ability(W) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,damage[lvl] + ad_scale[lvl] * owner.BONUS_AD, 6);

                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.physicalDmg;
        w.cooldown = new float[]{11,11,11,11,11};
        w.damage = new float[]{70,115,160,205,250};
        w.ad_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        w.cast_time = 0.25f;

        e = new Ability(E);

        r = new Ability(R) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD + ap_scale[lvl] * owner.AP, 6);

                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.physicalDmg;
        r.cooldown = new float[]{140,120,100};
        r.damage = new float[]{250,400,550};
        r.ad_scale = new float[]{1.15f,1.15f,1.15f};
        r.ap_scale = new float[]{0.7f,0.7f,0.7f,0.7f,0.7f};
        r.cast_time = 1;

        Item passiveOnhit = new SennaOnhit();
        addUniqueItem(passiveOnhit);

        extraVariableName = "Souls";
        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {AUTO, Q, W, R};
    }

    @Override
    public Champion makeCopy() {
        return new Senna(souls);
    }

    public static class SennaOnhit extends Item {
        public static final String name = "_Passive onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public SennaOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void extraDmg() {
            owner.passive.onUse(); //soul stacking
        }

        public void onHit() {
            owner.passive.onCall(); //onhit
            owner.passive.onUse(); //soul stacking
        }

        @Override
        public Item makeCopy() {
            return new Senna.SennaOnhit();
        }
    }
}
