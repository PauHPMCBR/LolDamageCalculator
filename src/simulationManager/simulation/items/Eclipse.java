package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Eclipse extends Item {
    public static final String name = "Eclipse";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3100;

    public Eclipse() {
        super(name, type, cost);
        ad = 60;
        ah = 15;
        lethality = 12;
    }

    public void specialStats() {
        item_cooldown = 6;
        if (owner.is_ranged) item_cooldown = 12;
    }

    public void extraDmg() {
        //ignoring needs 2 hits to proc, ms and shield granted
        if (canUse()) {
            putOnCooldown();
            if (owner.is_ranged)
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (0.03 * owner.getEnemy().getMaxHP()), 1);
            else
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, (float) (0.06 * owner.getEnemy().getMaxHP()), 1);
        }
    }

    public void applyMythicPassive() {
        owner.increaseArmorPen(4 * owner.legendary_items_carried);
        //ignoring +5 bonus ms
    }

    @Override
    public Item makeCopy() {
        return new Eclipse();
    }
}
