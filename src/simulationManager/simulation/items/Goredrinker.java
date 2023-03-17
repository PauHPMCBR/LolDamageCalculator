package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Goredrinker extends Item {
    public static final String name = "Goredrinker";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3300;

    public Goredrinker() {
        super(name, type, cost);
        ad = 55;
        ah = 20;
        hp = 300;
        omnivamp = 8;
        item_cooldown = 15;
    }

    public void extraDmg() {
        if (canUse()) {
            lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (1.75 * owner.BASE_AD));
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_HP += 50 * owner.legendary_items_carried;
        owner.AH += 3 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new Goredrinker();
    }
}
