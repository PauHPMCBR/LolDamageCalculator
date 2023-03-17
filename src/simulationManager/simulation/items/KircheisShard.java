package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class KircheisShard extends Item {
    public static final String name = "Kircheis Shard";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 700;

    int energize;

    public KircheisShard() {
        super(name, type, cost);
        as = 15;

        energize = 9;
    }

    public void onHit() {
        ++energize;
        if (energize == 10) { //kinda arbitrary
            energize = 0;
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 80);
        }
    }

    @Override
    public Item makeCopy() {
        return new KircheisShard();
    }
}
