package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Galeforce extends Item {
    public static final String name = "Galeforce";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3400;

    public Galeforce() {
        super(name, type, cost);
        ad = 60;
        as = 20;
        crit = 20;
        item_cooldown = 110;
    }

    public void extraDmg() {
        if (canUse()) {
            float missing = (float) Math.min(0.7, owner.getEnemy().getRelativeMissingHP());
            if (missing < 0.7) return; //this way, the execute multiplier is maxed

            putOnCooldown();
            float amount = 180;
            if (owner.lvl >= 10) amount += 15 * (owner.lvl - 9);
            amount += 0.45 * owner.BONUS_AD;
            amount += amount * missing * 5 / 7;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, amount);
        }
    }

    //ignoring +2% ms mythic passive

    @Override
    public Item makeCopy() {
        return new Galeforce();
    }
}
