package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ImperialMandate extends Item {
    public static final String name = "Imperial Mandate";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2500;

    float dmg;

    public ImperialMandate() {
        super(name, type, cost);
        ap = 40;
        ah = 20;
        hp = 200;
        manaRegen = 100;
        item_cooldown = 6;
    }

    public void specialStats() {
        dmg = 45 + 30f / 17f * (owner.lvl - 1); //extraVariable = damage
    }
    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, dmg, 1); //supposing every ability slows or cc
        }
    }
    public void applyMythicPassive() {
        owner.AP += 15 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new ImperialMandate();
    }
}
