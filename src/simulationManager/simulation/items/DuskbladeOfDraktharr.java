package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DuskbladeOfDraktharr extends Item {
    public static final String name = "Duskblade of Draktharr";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3100;

    public DuskbladeOfDraktharr() {
        super(name, type, cost);
        ad = 60;
        lethality = 18;
        ah = 20;
        item_cooldown = 15;
    }

    public void onHit() {
        if (canUse()) {
            putOnCooldown();
            if (owner.is_ranged)
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 55 + 25 * owner.BONUS_AD, 1);
            else
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 75 + 30 * owner.BONUS_AD, 1);
        }
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
        //ignoring +5 bonus ms
    }

    @Override
    public Item makeCopy() {
        return new DuskbladeOfDraktharr();
    }
}
