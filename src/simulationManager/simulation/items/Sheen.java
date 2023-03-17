package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Sheen extends Item {
    public static final String name = "Sheen";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 700;

    public Sheen() {
        super(name, type, cost);
        item_cooldown = 1.5f;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD, 1);
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new Sheen();
    }
}
