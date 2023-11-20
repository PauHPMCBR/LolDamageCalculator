package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Zed extends Champion {
    public static final String name = "Zed";

    public Zed() {
        super(
                name,
                644f,
                99f,
                0f, //energy, in this case
                0f,
                32f,
                4.7f,
                63f,
                3.4f,
                29f,
                2.05f,
                1.75f,
                0.651f,
                2.5f,
                19.759f,
                0.651f,
                3.3f,
                false
        );



        passive = new Ability(PASSIVE) {
            public void startingCalculations() {
                owner.MANA = 0; //to make sure things like muramana don't work. He cannot gain mana.
            }
            public void onCall() {
                if (currentCooldown > 0.05) return;
                if (owner.getEnemy().getRelativeMissingHP() < 0.5) return;

                float mult = 0.06f;
                if (owner.lvl >= 7) mult = 0.08f;
                if (owner.lvl >= 17) mult = 0.1f;
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, owner.getEnemy().getMaxHP() * mult, 3);
                currentCooldown = 10;
            }
        };

        q = new Ability(Q) {
            public void onUse() {
                for (int i = 0; i < owner.w.extraVariable + owner.r.extraVariable + 1; ++i) //apply as many shurikens as shadows present + 1
                    damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (damage[lvl] + ad_scale[lvl] * owner.BONUS_AD), 6);
                currentCooldown = getCooldown();
            }
        };
        q.damageType = DamageType.physicalDmg;
        q.cooldown = new float[]{6,6,6,6,6};
        q.damage = new float[]{70,105,140,175,210};
        q.ad_scale = new float[]{1.1f,1.1f,1.1f,1.1f,1.1f};
        q.cast_time = 0.25f;

        w = new Ability(W) { //extraVariable = is W shadow spawned (won't despawn after duration, but won't stack)
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onUse() {
                extraVariable = 1;
                currentCooldown = getCooldown();
            }
        };
        w.damageType = null;
        w.cooldown = new float[]{20,19.25f,18.5f,17.75f,17};
        w.cast_time = 0;

        e = new Ability(E) {
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, damage[lvl] + ad_scale[lvl] * owner.BONUS_AD, 6);
                owner.w.currentCooldown -= 2;
                currentCooldown = getCooldown();
            }
        };
        e.damageType = DamageType.physicalDmg;
        e.cooldown = new float[]{5,4.5f,4,3.5f,3};
        e.damage = new float[]{65,85,105,125,145};
        e.ad_scale = new float[]{0.65f,0.65f,0.65f,0.65f,0.65f};
        e.cast_time = 0f;

        r = new Ability(R) { //extraVariable = is R shadow spawned
            float oldPhysical, oldMagic;
            public void startingCalculations() {
                extraVariable = 0;
            }
            public void onUse() {
                oldPhysical = cs.damage.totalPhysical; //store damage done before using r (true dmg does not count for r)
                oldMagic = cs.damage.totalMagic;
                extraVariable = 1;
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                float preDamage =
                        (cs.damage.totalPhysical - oldPhysical) / cs.damage.getPostPhysicalDamage(1)  +
                                (cs.damage.totalMagic - oldMagic) / cs.damage.getPostMagicDamage(1);
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, ad_scale[lvl] * owner.getAD() + ap_scale[lvl] * preDamage, 6);
            }
        };
        r.damageType = DamageType.physicalDmg;
        r.duration = new float[]{3,3,3};
        r.cooldown = new float[]{120,100,80};
        r.ad_scale = new float[]{0.65f,0.65f,0.65f};
        r.ap_scale = new float[]{0.25f,0.40f,0.55f}; //% of dmg dealt
        r.cast_time = 0.95f;


        //This special item will call the Passive on-hit effect
        Item passiveOnHit = new ZedOnhit();
        addUniqueItem(passiveOnHit);

        upgradeOrder = new AbilityType[] {Q, E, W, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {R, W, E, Q, AUTO};
    }

    @Override
    public Champion makeCopy() {
        return new Zed();
    }



    public static class ZedOnhit extends Item {
        public static final String name = "_passive onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public ZedOnhit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.passive.onCall();
        }

        @Override
        public Item makeCopy() {
            return new ZedOnhit();
        }
    }
}
