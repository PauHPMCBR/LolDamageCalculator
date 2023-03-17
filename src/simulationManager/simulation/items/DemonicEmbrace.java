package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DemonicEmbrace extends Item {
    public static final String name = "Demonic Embrace";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    float lastTick;

    public DemonicEmbrace() {
        super(name, type, cost);
        ap = 75;
        hp = 350;

        lastTick = 0;
    }

    public void specialStats() {
        owner.AP += owner.BONUS_HP * 0.02;
    }

    public void extraDmg() {
        float ticks = cs.time - lastTick; //extra variable is time
        float mult = 0.064f;
        if (owner.is_ranged) mult = 0.04f;
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg,
                (ticks * (owner.getEnemy().getMaxHP() * mult)), 1);
        lastTick = cs.time;
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new DemonicEmbrace();
    }
}
