package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Stridebreaker extends Item {
    public static final String name = "Stridebreaker";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3300;

    public Stridebreaker() {
        super(name, type, cost);
        ad = 50;
        as = 20;
        ah = 20;
        hp = 300;
        item_cooldown = 15;
    }

    public void extraDmg() {
        if (canUse()) {
            lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
            damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (1.75 * owner.BASE_AD));
        }
    }

    //ignoring mythic passive +2% ms

    @Override
    public Item makeCopy() {
        return new Stridebreaker();
    }
}
