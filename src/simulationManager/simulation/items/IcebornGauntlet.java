package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class IcebornGauntlet extends Item {
    public static final String name = "Iceborn Gauntlet";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3000;

    public IcebornGauntlet() {
        super(name, type, cost);
        ah = 20;
        hp = 400;
        armor = 50;
        item_cooldown = 1.5f;
    }

    public void onHit() { //ignoring damage reduction part!
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD, 1);
            }
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_HP += 50 * owner.legendary_items_carried; //ignoring tenacity and slow resist
    }

    @Override
    public Item makeCopy() {
        return new IcebornGauntlet();
    }
}
