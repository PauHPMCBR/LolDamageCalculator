package simulationManager.simulation;

/**
 * Constructor class for any item and rune
 */
public class Item {
    protected Champion owner;
    CurrentState cs;
    
    private final String name;

    /**
     * Item stats
     */
    int ad, ap, as, ah;
    int hp, armor, mr;
    int mana = 0, crit = 0;
    int lethality = 0, armor_pen = 0, magic_pen = 0, percent_magic_pen = 0;

    public boolean is_hidden = false; //if the item is hidden, it won't be displayed in results messages

    float item_cooldown;
    float extraVariable; //every item may use this variable for something specific

    float lastUsed;
    float damageDealt;

    boolean is_mythic = false;
    public boolean is_legendary = false;
    boolean is_rune = false;

    public boolean isHidden() {
        return is_hidden;
    }
    public String getName() {
        return name;
    }
    public float getDamageDealt() {
        return damageDealt;
    }


    /**
     * Functions that get overridden with item instances
     */
    void specialStats() {} //will get called at the start of the simulationManager.simulation
    void applyMythicPassive() {}
    void onHit() {} //will get called by autos
    void extraDmg() {} //will get called by abilities


    /**
     * Applies basic stats the item has to the item's owner
     */
    final void applyStats() {
        damageDealt = 0;
        lastUsed = -500;
        owner.BONUS_AD += ad;
        owner.AP += ap;
        owner.BONUS_AS += as;
        owner.AH += ah;
        owner.BONUS_HP += hp;
        owner.ARMOR += armor;
        owner.MAGIC_RESIST += mr;
        owner.MANA += mana;
        owner.CRIT_CHANCE += crit;
        owner.LETHALITY += lethality;
        owner.increaseArmorPen(armor_pen); //!!
        owner.MAGIC_PEN += magic_pen;
        owner.PERCENTAGE_MAGIC_PEN += percent_magic_pen / 100f; //can do this cause only one item has this (aka ignoring sunderer+void)
    }


    public Item(String name, int ad, int ap, int as, int ah, int hp, int armor, int mr) {
        this.extraVariable = 0;
        this.name = name;
        this.ad = ad;
        this.ap = ap;
        this.as = as;
        this.ah = ah;
        this.hp = hp;
        this.armor = armor;
        this.mr = mr;
    }

}

