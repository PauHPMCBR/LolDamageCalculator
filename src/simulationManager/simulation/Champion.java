package simulationManager.simulation;

import java.util.ArrayList;
import java.util.List;
import static simulationManager.simulation.AbilityType.*;

public abstract class Champion {
    CurrentState cs;

    public final String name;

    /**
     * Base champion stats and level scalings
     */
    public final float base_hp;
    public final float hp_growth;
    public final float base_mana;
    public final float mana_growth;
    public final float base_armor;
    public final float armor_growth;
    public final float base_ad;
    public final float ad_growth;
    public final float base_mr;
    public final float mr_growth;
    public float crit_damage;
    public final float base_as;
    public float max_as;
    public float attack_windup; //some abilities have to modify this
    public final float as_ratio;
    public final float bonus_as;
    public final boolean is_ranged;

    /**
     * Stats that can get modified by external factors like abilities and items
     */
    public float HP, MANA, ARMOR, MAGIC_RESIST, AP, CRIT_CHANCE, BONUS_AS, AH, ITEM_HASTE;
    public float BASE_AD, BONUS_AD, BASE_HP, BONUS_HP;
    public float LETHALITY, ARMOR_PEN, MAGIC_PEN, PERCENTAGE_MAGIC_PEN;
    public float ULTIMATE_HASTE;

    Inventory inventory = new Inventory();
    RunePage runes = null;
    List<Item> uniqueItems = new ArrayList<>(); //champion specific items that make certain tasks easier
    public List<Item> allItems;
    public int legendary_items_carried;

    public boolean alive = true;
    public int lvl;

    /**
     * Variables to keep track of very specific item behaviours
     */
    public boolean can_use_sheen;
    public float lastSheenProc;

    public Ability Passive = new Ability(AbilityType.passive);
    public Ability Q = new Ability(q);
    public Ability W = new Ability(w);
    public Ability E = new Ability(e);
    public Ability R = new Ability(r);
    public Ability[] allAbilities;
    public AbilityType[] upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e}; //default upgrade order
    public Ability getAbility(AbilityType a) {
        if (a == q) return Q;
        if (a == w) return W;
        if (a == e) return E;
        if (a == r) return R;
        if (a == passive) return Passive;
        return null;
    }

    public float autoCd;
    public float autoDmg;
    public int autosUsed;
    //private Random rng; <- was too inconsistent with comparisons. keep in mind comparing crit with no crit to have somewhat of a multiple of 5 auto amount

    public void increaseArmorPen(float amount) { //armor pen scales multiplicative
        if (amount > 1) amount /= 100;
        ARMOR_PEN = 1 - (1-ARMOR_PEN)*(1-amount);
    }
    public void increasePercentageMagicPen(float amount) {
        if (amount > 1) amount /= 100;
        PERCENTAGE_MAGIC_PEN = 1 - (1-PERCENTAGE_MAGIC_PEN)*(1-amount);
    }

    /**
     *  Comodity getters that do slight calculations
     */
    public Inventory getInventory() {
        return inventory;
    }
    public List<Rune> getRunes() {
        if (runes == null) return null;
        return runes.runeList;
    }
    public Champion getEnemy() {
        return this.equals(cs.champion) ? cs.enemy : cs.champion;
    }
    public float getAD() {
        return BASE_AD + BONUS_AD;
    }
    public float getAttackSpeed() {
        return Math.min(max_as, base_as * (1 + (BONUS_AS *as_ratio)/100));
    }
    public float getMaxHP() {return BASE_HP + BONUS_HP;}
    public float getMissingHP() {
        return getMaxHP()-HP;
    }
    public float getRelativeMissingHP() {
        return getMissingHP()/getMaxHP();
    }
    public float getCooldownModification() {
        return 100/(100+AH);
    }
    public float getItemCooldownModification() {
        return 100/(100+ITEM_HASTE);
    }
    public float getAttackWindupTime() {
        return 1 / getAttackSpeed() * attack_windup/100 * (1 + BONUS_AS/100);
    }
    public DamageType getAdaptive() {
        return (BONUS_AD*0.4 >= AP*0.25) ? DamageType.physicalDmg : DamageType.magicDmg;
    }


    /**
     * Funtions to set champion's inventories and runes
     */
    public void addUniqueItem(Item i) {
        uniqueItems.add(i);
    }
    public void setInventory(Inventory i) {
        inventory = new Inventory(i);
    }
    public void setRunePage(RunePage r) {
        runes = new RunePage(r);
    }


    /**
     *  Function called to "do damage", should only be called from the class "Damage"
     */
    void substractHP(float amount) {
        HP -= amount;
        if (HP <= 0) alive = false;
    }

    /**
     * All initializers (modifiable stats are calculated. Items, runes and abilities apply their effects and get owner)
     */
    void initializeItems() {
        int legendaries = 0;
        for (Item item : allItems) {
            item.owner = this;
            item.cs = cs;

            item.damageDealt = 0;
            item.applyStats();
            if (item.type == ItemType.legendary) ++legendaries;
        }
        legendary_items_carried = legendaries;
        for (Item item : allItems)
            if (item.type == ItemType.mythic) item.applyMythicPassive();
    }

    void initializeRunes() {
        for (Rune rune : runes.runeList) {
            rune.owner = this;
            rune.cs = cs;
            rune.damageDealt = 0;
        }
    }

    void initializeValues(CurrentState currentState) {
        this.cs = currentState;

        BASE_HP = base_hp + hp_growth*(lvl-1);
        BONUS_HP = 0;
        MANA = base_mana + mana_growth*(lvl-1);
        ARMOR = base_armor + armor_growth*(lvl-1);
        MAGIC_RESIST = base_mr + mr_growth*(lvl-1);
        AP = 0;
        CRIT_CHANCE = 0;
        BONUS_AS = bonus_as*(lvl-1);
        AH = 0;
        ULTIMATE_HASTE = 0;
        BASE_AD = base_ad + ad_growth*(lvl-1);
        BONUS_AD = 0;

        legendary_items_carried = 0;
        alive = true;
        lastSheenProc = -10000;
        autoDmg = 0;
        autosUsed = 0;
        autoCd = 0;

        allItems = new ArrayList<>();
        allItems.addAll(uniqueItems);
        allItems.addAll(inventory.getItems());

        allAbilities = new Ability[]{Passive, Q, W, E, R};

        for (Ability a : allAbilities) {
            a.owner = this;
            a.cs = cs;
            a.lvl = -1;
            a.damageDealt = 0;
            a.currentCooldown = 0;
            a.active = false;
            a.extraVariable = 0;
        }

        for (int i = 0; i < lvl; ++i) {
            switch (upgradeOrder[i]) {
                case q -> ++Q.lvl;
                case w -> ++W.lvl;
                case e -> ++E.lvl;
                case r -> ++R.lvl;
                default -> {}
            }
        }
    }

    /**
     * No ability for auto attacking, this function does just that. also calls for on-hit effects
     */
    public void autoAttack() {
        boolean isCrit = (autosUsed%5) < CRIT_CHANCE/100 * 5; //works for every 20% crit chance, wouldn't work with 15% cloak/zeal, non-random cycle of 5
        if (isCrit) autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD() * crit_damage);
        else autoDmg += cs.damage.applyDamage(DamageType.physicalDmg, getAD());
        ++autosUsed;
        autoCd = 1/getAttackSpeed();

        for (Item i : allItems) i.onHit();
        for (Rune r : runes.runeList) r.onHit();
    }
    public void autoReset() {
        autoCd = 0;
    }

    /**
     *  Calls the ability effects on use, updates sheen time and calls for extra damage from items (like ludens or comet)
     */
    public void useAbility(Ability a) {
        a.onUse();
        if (cs.time - lastSheenProc >= 1.5) can_use_sheen = true;
        if (a.damageType != null) {
            for (Item i : allItems) i.extraDmg();
            for (Rune r : runes.runeList) r.extraDmg();
        }
    }

    public Champion(String name,
                    float base_hp,
                    float hp_growth,
                    float base_mana,
                    float mana_growth,
                    float base_armor,
                    float armor_growth,
                    float base_ad,
                    float ad_growth,
                    float base_mr,
                    float mr_growth,
                    float crit_damage,
                    float base_as,
                    float max_as,
                    float attack_windup,
                    float as_ratio,
                    float bonus_as,
                    boolean is_ranged) {
        this.name = name;
        this.base_hp = base_hp;
        this.hp_growth = hp_growth;
        this.base_mana = base_mana;
        this.mana_growth = mana_growth;
        this.base_armor = base_armor;
        this.armor_growth = armor_growth;
        this.base_ad = base_ad;
        this.ad_growth = ad_growth;
        this.base_mr = base_mr;
        this.mr_growth = mr_growth;
        this.crit_damage = crit_damage;
        this.base_as = base_as;
        this.max_as = max_as;
        this.attack_windup = attack_windup;
        this.as_ratio = as_ratio;
        this.bonus_as = bonus_as;
        this.is_ranged = is_ranged;
    }

    /**
     *  Constructor to create a copy of the champion, so it can safely get modified during the simulationManager.simulation without hurting the original copy
     */
    public abstract Champion makeCopy();

    public Champion makeCopyWithoutReset() {
        Champion c = makeCopy();
        c.inventory = new Inventory(this.inventory);
        c.runes = new RunePage(this.runes);
        c.lvl = this.lvl;
        c.upgradeOrder = this.upgradeOrder;
        return c;
    }
}