package com.damagecalculator.simulationManager.simulation;

/**
 * Constructor class for any item
 */
public abstract class Item {
    public Champion owner;
    public CurrentState cs;
    
    public final String name;
    public final ItemType type;
    public final int cost;

    public String extraVariableName = null;

    /**
     * Item stats
     */
    public int ad, ap, as, ah;
    public int hp, armor, mr;
    public int mana = 0, crit = 0, mana_regen, hp_regen;
    public int lethality = 0, armor_pen = 0, magic_pen = 0, percent_magic_pen = 0;
    public int lifesteal, hsp, ms, percent_ms; //currently unused

    public boolean is_hidden = false; //if the item is hidden, it won't be displayed in results messages

    public float item_cooldown;

    public float lastUsed;
    public float damageDealt;
    public int getDamageDealt() {
        return Math.round(damageDealt);
    }

    public final void putOnCooldown() {
        lastUsed = cs.time - item_cooldown * (1 - owner.getItemCooldownModification());
    }
    public final boolean canUse() {
        return cs.time > lastUsed + item_cooldown;
    }


    public void specialStats() {}
    public void onHit() {} //will get called by autos
    public void extraDmg() {} //will get called by abilities


    /**
     * Applies basic stats the item has to the item's owner
     */
    final void applyStats() {
        owner.BONUS_AD += ad;
        owner.AP += ap;
        owner.BONUS_AS += as;
        owner.AH += ah;
        owner.BONUS_HP += hp;
        owner.ARMOR += armor;
        owner.MAGIC_RESIST += mr;
        owner.MANA += mana;
        owner.CRIT_CHANCE += crit;
        owner.MANA_REGEN += mana_regen;
        owner.HP_REGEN += hp_regen;
        owner.LETHALITY += lethality;
        owner.increaseArmorPen(armor_pen);
        owner.MAGIC_PEN += magic_pen;
        owner.increasePercentageMagicPen(percent_magic_pen);
    }

    public Item(String name, ItemType type, int cost) {
        damageDealt = 0;
        lastUsed = -500;

        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object i) {
        if (!(i instanceof Item)) return false;
        return ((Item) i).name.equals(this.name);
    }

    public abstract Item makeCopy();
}

