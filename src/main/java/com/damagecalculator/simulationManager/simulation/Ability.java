package com.damagecalculator.simulationManager.simulation;

/**
 * Constructor class for any ability
 */
public class Ability {
    protected Champion owner;
    protected CurrentState cs;

    public final AbilityType type;

    public int lvl = -1;
    public boolean isUnlocked() {
        return lvl >= 0;
    }

    public float[] duration;
    public float[] cooldown;
    public float[] damage;
    public float[] ad_scale;
    public float[] ap_scale;
    public float cast_time;
    public DamageType damageType;


    public float currentCooldown;
    public boolean active;

    public float extraVariable = 0; //every ability may use this variable for something specific
    public float damageDealt = 0;

    public float getCooldown() {
        if (cooldown == null) return -1;
        if (type.equals(AbilityType.r))
            return cooldown[lvl] * 100/(100+(owner.AH + owner.ULTIMATE_HASTE));
        return cooldown[lvl] * owner.getCooldownModification();
    }
    public float getDuration() {
        if (duration == null) return -1;
        return duration[lvl];
    }
    public int getDamageDealt() {
        return Math.round(damageDealt);
    }

    /**
     * Functions that get overridden with ability instances
     */
    public void startingCalculations() {} //will get called at the start of the simulation, after item calculations
    public void onUse() {} //will get called when the ability is used
    public void onCall() {} //will get called by other abilities or champion unique items (aka specific circumstances that are not ability uses)
    public void onExpiring() {} //will get called when the ability expires (if it has a duration)

    public Ability(AbilityType type) {
        this.type = type;
    }
}
