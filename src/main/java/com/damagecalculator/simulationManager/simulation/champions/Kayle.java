package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Kayle extends Champion {
    public static final String name = "Kayle";

    public Kayle() {
        super(
                name,
                670f,
                92f,
                330f,
                50f,
                26f,
                4.2f,
                50f,
                2.5f,
                22f,
                1.3f,
                1.75f,
                0.625f,
                3f,
                19.355f,
                0.667f,
                1.5f,
                false
        );

        passive = new Ability(AbilityType.PASSIVE) {
            public void startingCalculations() {
                extraVariable = 0;
                if (owner.lvl >= 6) owner.is_ranged = true;
                if (owner.lvl >= 16) {
                    for (int i = 0; i < 5; ++i) onCall();
                }
            }

            public void onCall() {
                if (owner.lvl >= 11 && extraVariable == 5) {
                    float dmg = 20 + 3*(owner.lvl - 11);
                    dmg += 0.1f * owner.BONUS_AD + 0.25f * owner.AP;
                    if (owner.wasLastAutoCrit()) dmg *= owner.crit_damage;
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 3);
                }

                if (extraVariable < 5) {
                    owner.BONUS_AS += 6 + 0.01f * owner.AP;
                    ++extraVariable;
                }
            }
        };

        q = new Ability(Q) {
            public void startingCalculations() {
                cast_time = owner.getAttackWindupTime();
            }
            public void onUse() {
                float dmg = damage[lvl] + ad_scale[lvl]*owner.BONUS_AD;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg * 1.5f, 6);

                owner.getEnemy().ARMOR *= 0.85f;
                owner.getEnemy().MAGIC_RESIST *= 0.85f;

                cast_time = owner.getAttackWindupTime();
                currentCooldown = getCooldown();
            }

            public void onExpiring() {
                owner.getEnemy().ARMOR /= 0.85f;
                owner.getEnemy().MAGIC_RESIST /= 0.85f;
            }
        };
        q.damageType = DamageType.magicDmg;
        q.cooldown = new float[]{12,11,10,9,8};
        q.damage = new float[]{60,100,140,180,220};
        q.ad_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        q.ap_scale = new float[]{0.5f,0.5f,0.5f,0.5f,0.5f};
        q.duration = new float[]{4,4,4,4,4};

        w = new Ability(W) {
            public void onUse() {
                currentCooldown = getCooldown();
            }
        };
        w.cooldown = new float[]{15,15,15,15,15};

        e = new Ability(E) {
            public void onUse() {
                owner.autoReset();

                float percentMissing = 8 + 0.5f * lvl + 0.015f * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        owner.getEnemy().getMissingHP() * percentMissing/100f, 6);

                currentCooldown = getCooldown();
            }

            public void onCall() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ad_scale[lvl] + owner.BONUS_AD + ap_scale[lvl] * owner.AP, 3);
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{8,7.5f,7,6.5f,6};
        e.damage = new float[]{15,20,25,30,35};
        e.ad_scale = new float[]{0.1f,0.1f,0.1f,0.1f,0.1f};
        e.ap_scale = new float[]{0.2f,0.2f,0.2f,0.2f,0.2f};

        r = new Ability(R) {
            public void onUse() {
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        damage[lvl] + ad_scale[lvl] * owner.BONUS_AD + ap_scale[lvl] * owner.AP, 6);
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{160,120,80};
        r.damage = new float[]{200,300,400};
        r.ad_scale = new float[]{1,1,1};
        r.ap_scale = new float[]{0.7f,0.7f,0.7f};
        r.cast_time = 0.5f;
        r.duration = new float[]{2.5f,2.5f,2.5f};

        Item passiveOnHit = new KayleOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {E, Q, W, E, E, R, E, Q, E, Q, R, Q, Q, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {AUTO, E, Q};
    }

    @Override
    public Champion makeCopy() {
        return new Kayle();
    }


    public static class KayleOnhit extends Item {
        public static final String name = "_passive + E onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public KayleOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.passive.onCall();
            owner.e.onCall();
        }

        @Override
        public Item makeCopy() {
            return new KayleOnhit();
        }
    }
}
