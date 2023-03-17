package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ImmortalShieldbow extends Item {
    public static final String name = "Immortal Shieldbow";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3400;

    public ImmortalShieldbow() {
        super(name, type, cost);
        ad = 50;
        as = 20;
        crit = 20;
        lifesteal = 7;
        item_cooldown = 90; //although unused for now
    }

    public void applyMythicPassive() {
        owner.BONUS_AD += 5 * owner.legendary_items_carried;
        owner.BONUS_HP += 70 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new ImmortalShieldbow();
    }
}
