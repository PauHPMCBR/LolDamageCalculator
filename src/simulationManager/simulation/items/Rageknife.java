package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Rageknife extends Item {
    public static final String name = "Rageknife";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    float critChance;

    public Rageknife() {
        super(name, type, cost);
        as = 25;
    }

    public void specialStats() {
        critChance = owner.CRIT_CHANCE;//item cooldown is amount of crit (from 0 to 100)
        owner.CRIT_CHANCE = 0;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, critChance * 1.75f, 1);
    }


    @Override
    public Item makeCopy() {
        return new Rageknife();
    }
}
