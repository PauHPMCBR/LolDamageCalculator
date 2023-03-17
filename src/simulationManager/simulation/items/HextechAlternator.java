package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class HextechAlternator extends Item {
    public static final String name = "Hextech Alternator";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1050;

    public HextechAlternator() {
        super(name, type, cost);
        ap = 25;
        hp = 150;
        item_cooldown = 40;
    }

    public void extraDmg() {
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 50f + 75f / 17f * (owner.lvl -1));
        }
    }

    @Override
    public Item makeCopy() {
        return new HextechAlternator();
    }
}
