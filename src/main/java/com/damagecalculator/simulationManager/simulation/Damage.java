package com.damagecalculator.simulationManager.simulation;

/**
 * Handles the appliance of damage from the champion to the enemy
 */
public class Damage {
    CurrentState cs;

    /**
     * Damage dealt in total
     */
    public float totalPhysical = 0;
    public float totalMagic = 0;
    public float totalTrue = 0;
    public float getTotalDamage() {
        return totalMagic + totalPhysical + totalTrue;
    }

    private float applyTrueDamage(float amount) {
        cs.enemy.substractHP(amount);
        totalTrue += amount;
        return amount;
    }
    private float applyPhysicalDamage(float amount) {
        float armor = cs.enemy.ARMOR;
        armor *= (1 - cs.champion.ARMOR_PEN);
        armor -= cs.champion.LETHALITY;
        if (armor < 0) armor = 0;
        amount *= (100/(100 + armor));
        cs.enemy.substractHP(amount);
        totalPhysical += amount;
        return amount;
    }
    private float applyMagicDamage(float amount) {

        float mr = cs.enemy.MAGIC_RESIST;
        mr *= (1 - cs.champion.PERCENTAGE_MAGIC_PEN);
        mr -= cs.champion.MAGIC_PEN;
        if (mr < 0) mr = 0;
        amount *= (100/(100 + mr));
        amount *= cs.liandryPercent; //!
        cs.enemy.substractHP(amount);
        totalMagic += amount;
        return amount;
    }

    /**
     * Only call to skip damage multipliers, very specific uses
     */
    public float applyDamage(DamageType type, float amount, boolean bypassExtras) {
        if (!bypassExtras) return applyDamage(type, amount);

        if (type == DamageType.physicalDmg) return applyPhysicalDamage(amount);
        if (type == DamageType.magicDmg) return applyMagicDamage(amount);
        return applyTrueDamage(amount);
    }

    /**
     * Main function to call from outside that will do all calculations prior to the damage appliance
     * Damage instance types:
     *      odd (1,3...)            :   "proc" damage. will not count for black cleaver or ludens
     *      multiple of 3 (3,6...)  :   "ability" damage. will count for navori amplifier
     *
     * Examples:
     *      0 comes from auto
     *      1 comes from item on-hit
     *      3 comes from ability passive
     *      6 comes from ability active
     */
    public float applyDamage(DamageType type, float amount, int damageInstanceType) {
        amount *= cs.damageTrueMultiplier;
        if (cs.riftmakerItem != null) { //(riftmaker amplifies true damage as well)
            if (cs.time >= 3) cs.riftmakerItem.damageDealt += applyTrueDamage((float) (amount*0.09));
            else cs.riftmakerItem.damageDealt += applyDamage(type, (float) (amount * 0.03* cs.time), true); //in theory it's every second increase
        }
        if (type != DamageType.trueDmg) {
            amount *= cs.damageMultiplier;
            if (cs.hasCutDown) {
                if (cs.enemy.getMaxHP() > cs.champion.getMaxHP() * 1.1) {
                    float x = (float) Math.max(0.1, Math.min(1, cs.enemy.getMaxHP() / cs.champion.getMaxHP() - 1));
                    amount *= 1 + (x - 0.1) / 9 + 0.05;
                }
            }
            else if (cs.hasCoupDeGrace) {
                if (cs.enemy.getRelativeMissingHP() > 0.6) {
                    amount *= 1.08;
                }
            }
            //last stand can be controversial, since we don't know champion's relative hp. currently skipped
        }
        if (damageInstanceType%3 == 0) { //damage from ability
            amount *= cs.navoriPercent; //(navori amplifies tru damage as well)
        }

        float dmg = applyDamage(type, amount, true);

        if (cs.hasFirstStrike) {
            cs.firstStrikeRune.damageDealt += applyDamage(DamageType.trueDmg, dmg * 0.09f, true);
        }

        if (damageInstanceType%2 == 0) { //not proc damage
            if (cs.cleaverItem != null && type == DamageType.physicalDmg) cs.cleaverItem.increaseCarveStacks();
            if (cs.ludensItem != null && type == DamageType.magicDmg) cs.ludensItem.echo();
        }

        return dmg;
    }
    public float applyDamage(DamageType type, float amount) {
        return applyDamage(type, amount, 0);
    }

    /**
     * Called by true executes (aka instantly kill the enemy) like collector, Pyke or Syndra ult...
     */
    public void execute() {
        applyDamage(DamageType.trueDmg, cs.enemy.HP+10, true);
    }

    public Damage(CurrentState cs) {
        this.cs = cs;
    }
}
