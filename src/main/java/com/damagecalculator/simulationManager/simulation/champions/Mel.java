package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Mel extends Champion {
    public static final String name = "Mel";

    private int overwhelmStacks;
    private int searingBrillianceStacks;

    public Mel() {
        super (name,
                630f,
                93f,
                480f,
                28f,
                21f,
                5.2f,
                54f,
                3.3f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                3f,
                15.6f,
                0.4f,
                2.5f,
                true
        );

        passive = new Ability(PASSIVE) {
            public void startingCalculations() {
                overwhelmStacks = 0;
                searingBrillianceStacks = 0;
            }

            //overwhelm stacking
            public void onUse() {
                ++overwhelmStacks;
                float dmg = 50 + 10*owner.r.lvl + 0.1f*owner.AP;
                dmg += (overwhelmStacks - 1)*(2 + owner.r.lvl + 0.075f*owner.AP);
                float postMitDmg = cs.damage.getPostMitigationDamage(DamageType.magicDmg, dmg, false);
                if (getEnemy().getMissingHP() <= postMitDmg) {
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, false);
                }
            }

            //onHit
            public void onCall() {
                float dmg = 7 + owner.lvl + 0.01f*owner.AP;
                for (int i = 0; i < searingBrillianceStacks; ++i) {
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, false);
                    onUse();
                }

                searingBrillianceStacks = 0;
                onUse();
            }
        };

        q = new Ability(Q) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                for (int i = 0; i < duration[lvl]; ++i) {
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, true);
                    passive.onUse();
                }

                searingBrillianceStacks += 3;
                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.magicDmg;
        q.cooldown = new float[]{10,9,8,7,6};
        q.damage = new float[]{13,15.5f,18,20.5f,23};
        q.duration = new float[]{6,7,8,9,10}; //number of bolts
        q.ap_scale = new float[]{0.085f,0.085f,0.085f,0.085f,0.085f};
        q.cast_time = 0.25f;

        w = new Ability(W) {
            public void onUse() {
                searingBrillianceStacks += 3;
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.magicDmg;
        w.cooldown = new float[]{35,32,29,26,23};
        w.damage = new float[]{40,45,50,55,60}; //% reflected
        w.ap_scale = new float[]{0.0005f,0.0005f,0.0005f,0.0005f,0.0005f}; //% reflected ap scale
        w.cast_time = 0;

        e = new Ability(E) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, true);
                passive.onUse();

                //16 dot hits max. min is 8 if rooted by center
                int ticks = 16;
                float fieldDmgPerTick = 2*lvl + 0.01f*owner.AP;
                for (int i = 0; i < ticks; ++i) {
                    damageDealt += cs.damage.applyDamage(DamageType.magicDmg, fieldDmgPerTick, true);
                    passive.onUse();
                }

                searingBrillianceStacks += 3;
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{12,11.5f,11,10.5f,10};
        e.damage = new float[]{60,105,150,195,240};
        e.ap_scale = new float[]{0.6f,0.6f,0.6f,0.6f,0.6f};
        e.cast_time = 0.25f;

        r = new Ability(R) {
            public void onUse() {
                float dmg = damage[lvl] + ap_scale[lvl] * owner.AP;
                dmg += overwhelmStacks * (1 + 3*lvl + 0.035f*owner.AP);
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, true);
                passive.onUse();

                searingBrillianceStacks += 3;
                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.magicDmg;
        r.cooldown = new float[]{120,100,80};
        r.damage = new float[]{100,150,200};
        r.ap_scale = new float[]{0.3f,0.3f,0.3f};
        r.cast_time = 0.75f;

        Item passiveOnHit = new MelOnHit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {E, Q, W, E, E, R, E, Q, E, Q, R, Q, Q, W, W, R, W, W};
        abilityPriorities = new AbilityType[] {E, W, Q, AUTO, R};
    }

    @Override
    public Champion makeCopy() {
        return new Mel();
    }

    public static class MelOnHit extends Item {
        public static final String name = "_mel passive onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public MelOnHit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.passive.onCall();
        }

        @Override
        public Item makeCopy() {
            return new MelOnHit();
        }
    }
}
