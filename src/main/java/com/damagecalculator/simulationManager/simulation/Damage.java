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

    public float getPostPhysicalDamage(float amount) {
        float armor = cs.enemy.ARMOR;
        armor *= (1 - cs.champion.ARMOR_PEN);
        armor -= cs.champion.LETHALITY;
        if (armor < 0) armor = 0;
        amount *= (100/(100 + armor));
        return amount;
    }
    public float getPostMagicDamage(float amount) {
        float mr = cs.enemy.MAGIC_RESIST;
        mr *= (1 - cs.champion.PERCENTAGE_MAGIC_PEN);
        mr -= cs.champion.MAGIC_PEN;
        if (mr < 0) mr = 0;
        amount *= (100/(100 + mr));
        return amount;
    }

    private float applyDirectDamage(DamageType type, float amount) {
        if (type == DamageType.physicalDmg) {
            amount = getPostPhysicalDamage(amount);
            totalPhysical += amount;
        }
        else if (type == DamageType.magicDmg) {
            amount = getPostMagicDamage(amount);
            totalMagic += amount;
        }
        else totalTrue += amount;
        cs.enemy.substractHP(amount);
        return amount;
    }

    // TODO add DOT and PET distinguishment for shadowflame
    public float getPostMitigationDamage(DamageType type, float amount, boolean isFromAbility) {
        amount *= cs.damageMultiplier;
        if (cs.hasCutDown) {
            if (cs.enemy.getRelativeMissingHP() < 0.4) {
                amount *= 1.08f;
            }
        }
        else if (cs.hasCoupDeGrace) {
            if (cs.enemy.getRelativeMissingHP() > 0.6) {
                amount *= 1.08f;
            }
        }
        //last stand can be controversial, since we don't know champion's relative hp. currently skipped
        if (isFromAbility) {
            amount *= cs.abilityDamageMultiplier;
        }

        if (cs.abyssalMaskItem != null && type == DamageType.magicDmg) { //12% increased dmg
            amount *= 1.12f;
        }

        if (type == DamageType.physicalDmg) amount = getPostPhysicalDamage(amount);
        else if (type == DamageType.magicDmg) amount = getPostMagicDamage(amount);

        /*if (cs.shadowflameItem != null && type != DamageType.physicalDmg) { //crit for true and magic dmg
            if (cs.enemy.getRelativeMissingHP() >= 0.6) { //"crit" if enemy below 40% hp
                amount *= 1.2f;
            }
        }*/
        return amount;
    }
    public float applyDamage(DamageType type, float amount, boolean isFromAbility) {
        amount *= cs.damageMultiplier;
        if (cs.hasCutDown) {
            if (cs.enemy.getRelativeMissingHP() < 0.4) {
                amount *= 1.08f;
            }
        }
        else if (cs.hasCoupDeGrace) {
            if (cs.enemy.getRelativeMissingHP() > 0.6) {
                amount *= 1.08f;
            }
        }
        //last stand can be controversial, since we don't know champion's relative hp. currently skipped
        if (isFromAbility) {
            amount *= cs.abilityDamageMultiplier;
        }

        float dmg = applyDirectDamage(type, amount);

        if (cs.abyssalMaskItem != null && type == DamageType.magicDmg) { //12% increased dmg
            float extraDmg = applyDirectDamage(DamageType.magicDmg, dmg * 0.12f);
            dmg += extraDmg;
            cs.abyssalMaskItem.damageDealt += extraDmg;
        }

        if (cs.shadowflameItem != null && type != DamageType.physicalDmg) { //crit for true and magic dmg
            if (cs.enemy.getRelativeMissingHP() >= 0.6) { //"crit" if enemy below 40% hp
                float extraDmg = applyDirectDamage(type, dmg * 0.2f); // ignoring pet and dot increase
                dmg += extraDmg;
                cs.shadowflameItem.damageDealt += extraDmg;
            }
        }

        if (cs.hasFirstStrike) {
            cs.firstStrikeRune.damageDealt += applyDirectDamage(DamageType.trueDmg, dmg * 0.07f);
        }

        //since 14.16 there's 0 cd on black cleaver stacks, so onhits and similar add stacks as well
        if (cs.cleaverItem != null && type == DamageType.physicalDmg) {
            cs.cleaverItem.increaseCarveStacks();
        }

        if (cs.bloodlettersCurseItem != null && type == DamageType.magicDmg && isFromAbility) {
            cs.bloodlettersCurseItem.increaseDecayStacks(); //ignoring 0.3s cd
        }

        return dmg;
    }

    /**
     * (not removing this beceause it would mean huge refactor)
     *
     * Main function to call from outside that will do all calculations prior to the damage appliance
     * Damage instance types:
     *      odd (1,3...)            :   "proc" damage. will not count for black cleaver or ludens (OUTDATED)
     *      multiple of 3 (3,6...)  :   "ability" damage. will count for navori amplifier
     *
     * Examples:
     *      0 comes from auto
     *      1 comes from item/rune on-hit
     *      2 comes from item/rune active / independent dmg
     *      3 comes from ability passive
     *      6 comes from ability active
     */
    public float applyDamage(DamageType type, float amount, int damageInstanceType) {
        return applyDamage(type, amount, damageInstanceType == 3 || damageInstanceType == 6);
    }

    /**
     * Called by true executes (aka instantly kill the enemy) like collector, Pyke or Syndra ult...
     */
    public void execute() {
        applyDirectDamage(DamageType.trueDmg, cs.enemy.HP+10);
    }

    public Damage(CurrentState cs) {
        this.cs = cs;
    }
}
