package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class LichBane extends Item {
    public static final String name = "Lich Bane";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public LichBane() {
        super(name, type, cost);
        ap = 75;
        ah = 15;
        ms = 8;
    }

    public void onHit() {
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                owner.lastSheenProc = cs.time;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                        (float) (0.75 * owner.BASE_AD + 0.5 * owner.AP), 1);
            }
        }
    }

        @Override
    public Item makeCopy() {
        return new LichBane();
    }
}
