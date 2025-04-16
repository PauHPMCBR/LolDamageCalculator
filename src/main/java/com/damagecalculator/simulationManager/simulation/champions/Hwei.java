package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Hwei extends Champion {
    public static final String name = "Hwei";

    public AbilityType secondAbility = null;

    public Hwei() {
        super (name,
                580f,
                109f,
                480f,
                30f,
                21f,
                4.7f,
                54f,
                3.3f,
                30f,
                1.3f,
                1.75f,
                0.69f,
                3f,
                18.729f,
                0.658f,
                2.5f,
                true
        );

        passive = new Ability(PASSIVE) {
            public void startingCalculations() {
                extraVariable = 0; //if marked enemy
            }
            public void onCall() {
                if (extraVariable == 1) {
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 35 + 195/17f*(owner.lvl-1) + 0.30f*owner.AP, 3);
                    extraVariable = 0;
                    w.onCall();
                }
                else extraVariable = 1;
            }
        };
        passive.damageType = DamageType.magicDmg;

        q = new Ability(Q) {
            public void onUse() {
                if (secondAbility == null || secondAbility == R) return;
                switch (secondAbility) {
                    case Q -> {
                        float baseDmg = 50 + 30*lvl;
                        float enemyMaxHP = 0.03f + 0.01f*lvl;
                        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, baseDmg + 0.7f*owner.AP + enemyMaxHP*owner.getEnemy().getMaxHP(), 6);
                    }
                    case W -> {
                        float baseDmg = 60 + 25*lvl;
                        float percentIncreasePerPercentMissing = 1 + 0.375f*lvl;
                        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                                (baseDmg + 0.25f*owner.AP) * (1 + owner.getEnemy().getRelativeMissingHP()*percentIncreasePerPercentMissing), 6);
                    }
                    case E -> {
                        float baseDmg = 70*(lvl+1);
                        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, baseDmg + 0.8f*owner.AP , 6);
                    }
                }
                w.onCall();
                passive.onCall();
                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.magicDmg;
        q.cooldown = new float[]{10,9,8,7,6};
        q.cast_time = 0.35f;

        w = new Ability(W) {
            boolean autoHasProccedPassive = true;
            public void onCall() {
                if (extraVariable > 0) {
                    --extraVariable;
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl]*owner.AP, 3);

                    if (owner.lastAbilityUsed == AUTO && !autoHasProccedPassive) {
                        passive.onCall();
                        autoHasProccedPassive = true;
                    }
                }
            }
            public void onUse() {
                if (secondAbility == null || secondAbility == R) return;
                if (secondAbility == E) {
                    extraVariable = 3;
                    autoHasProccedPassive = false;
                }
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{18,17.5f,17,16.5f,16};
        w.damage = new float[]{20,30,40,50,60};
        w.ap_scale = new float[]{0.15f,0.15f,0.15f,0.15f,0.15f};
        w.cast_time = 0;

        e = new Ability(E) {
            public void onUse() {
                if (secondAbility == null || secondAbility == R) return;

                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (damage[lvl] +
                        ap_scale[lvl] * owner.AP), 6);
                w.onCall();
                passive.onCall();
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{13,12.5f,12,11.5f,11};
        e.damage = new float[]{70,110,150,190,230};
        e.ap_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        e.cast_time = 0;

        r = new Ability(R) {
            public void onUse() {
                float dmg = (2.5f*(lvl+1) + 0.0125f*owner.AP);
                int despairTicks = 12;
                for (int i = 0; i < despairTicks; ++i)
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 6);
                w.onCall();
                passive.onCall();
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl]*owner.AP, 6);
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{140,115,80};
        r.damage = new float[]{200,325,450};
        r.ap_scale = new float[]{0.8f,0.8f,0.8f};
        r.duration = new float[]{3,3,3};
        r.cast_time = 0.25f;

        upgradeOrder = new AbilityType[] {Q, E, W, Q, Q, R, Q, E, Q, E, R, E, E, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {R,W,E,E,Q,Q,Q};
    }

    @Override
    public Champion makeCopy() {
        return new Hwei();
    }

    public static class WHweiOnhit extends Item {
        public static final String name = "_w onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public WHweiOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.w.onCall();
        }

        @Override
        public Item makeCopy() {
            return new WHweiOnhit();
        }
    }
}
