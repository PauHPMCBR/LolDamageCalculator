package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

import static com.damagecalculator.simulationManager.simulation.AbilityType.*;

public class Jinx extends Champion {
    public static final String name = "Jinx";

    public Jinx() {
        super(
                name,
                630f,
                105f,
                260f,
                50f,
                26f,
                4.7f,
                59f,
                3.15f,
                30f,
                1.3f,
                1.75f,
                0.625f,
                3f,
                16.875f,
                0.625f,
                1.4f,
                true
        );

        passive = new Ability(PASSIVE);

        q = new Ability(Q) {
            public void startingCalculations(){
                extraVariable = 0;
            }
            public void onCall() {
                if (!isUnlocked()) return;
                if (extraVariable == 3) return;
                ++extraVariable;
                owner.BONUS_AS += q.damage[lvl];
                if (extraVariable == 1) owner.BONUS_AS += q.damage[lvl];
            }
            public void onUse() {
                boolean isCrit = (autosUsed%5) < CRIT_CHANCE/100 * 5; //works for every 20% crit chance, wouldn't work with 15% cloak/zeal, non-random cycle of 5
                if (isCrit) autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD() * 1.1f * crit_damage, 0);
                else autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD() * 1.1f, 0);
                ++autosUsed;
                autoCd = 1/getAttackSpeed(); //<- this is just in case it switches back to normal autos, they have this cd

                float extraASGiven = 0;
                if (extraVariable != 0) extraASGiven = q.damage[lvl] * (extraVariable+1);
                owner.BONUS_AS -= extraASGiven;
                owner.BONUS_AS *= 0.9f;
                currentCooldown = 1/getAttackSpeed(); //q "cd" doesn't include q bonus AS, and works with 10% less bonus AS
                owner.BONUS_AS /= 0.9f;
                owner.BONUS_AS += extraASGiven;

                owner.applyOnhit(1, JinxOnHit.name); //onhit except q attack speed buff
            }
        };
        q.damageType = null;
        q.damage = new float[]{7.5f,13.75f,20f,26.25f,32.5f}; //attack speed per q stack
        q.cast_time = 0;
        q.cooldown = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f}; //unused

        w = new Ability(W) {
            public void startingCalculations() {
                cast_time = 0.6f - Math.min(owner.BONUS_AS, 250)/1250;
            }
            public void onUse() {
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, damage[lvl] + ad_scale[lvl] * owner.getAD(), 6);
                cast_time = 0.6f - Math.min(owner.BONUS_AS, 250)/1250;
                currentCooldown = getCooldown();
            }
        };
        w.damageType = DamageType.physicalDmg;
        w.cooldown = new float[]{8,7,6,5,4};
        w.damage = new float[]{10,60,110,160,210};
        w.ad_scale = new float[]{1.4f,1.4f,1.4f,1.4f,1.4f};

        e = new Ability(E) {
            public void onUse() {
                currentCooldown = getCooldown();
            }
            public void onExpiring() {
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg, damage[lvl] + ap_scale[lvl] * owner.AP, 6);
            }
        };
        e.damageType = DamageType.magicDmg;
        e.cooldown = new float[]{24,20.5f,17,13.5f,10};
        e.damage = new float[]{70,120,170,220,270};
        e.ap_scale = new float[]{1,1,1,1,1};
        e.duration = new float[]{0.9f,0.9f,0.9f,0.9f,0.9f};
        e.cast_time = 0;

        r = new Ability(R) {
            public void onUse() {
                //supposing it's shot at ~700 units, equal to 50% of its total dmg (only the base dmg, not the "execute" dmg)
                float dmg = (damage[lvl] + ad_scale[lvl] * owner.BONUS_AD) * 0.5f;
                dmg += ap_scale[lvl] * owner.getEnemy().getMissingHP();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 6);

                currentCooldown = getCooldown();
            }
        };
        r.damageType = DamageType.physicalDmg;
        r.damage = new float[]{325,475,625};
        r.ad_scale = new float[]{1.65f,1.65f,1.65f};
        r.ap_scale = new float[]{0.25f,0.30f,0.35f}; //missing hp dmg
        r.cooldown = new float[]{85,65,45};
        r.cast_time = 0.6f;


        //This special item will call the Q and W on-hit effects
        Item qOnHit = new JinxOnHit();
        addUniqueItem(qOnHit);

        upgradeOrder = new AbilityType[] {Q, W, E, Q, Q, R, Q, W, Q, W, R, W, W, E, E, R, E, E};
        abilityPriorities = new AbilityType[] {AUTO, W, E, R};
    }

    @Override
    public Champion makeCopy() {
        return new Jinx();
    }



    public static class JinxOnHit extends Item {
        public static final String name = "_q onhit";
        public static final ItemType type = ItemType.UNIQUE;
        public static final int cost = 0;

        public JinxOnHit() {
            super(name, type, cost);
            is_hidden = true;
        }

        public void onHit() {
            owner.q.onCall();
        }

        @Override
        public Item makeCopy() {
            return new JinxOnHit();
        }
    }
}
