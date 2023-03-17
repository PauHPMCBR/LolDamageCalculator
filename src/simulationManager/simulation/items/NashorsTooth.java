package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class NashorsTooth extends Item {
    public static final String name = "Nashor's Tooth";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public NashorsTooth() {
        super(name, type, cost);
        ap = 100;
        as = 50;
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.magicDmg, 15 + 0.2f * owner.AP, 1);
    }

    @Override
    public Item makeCopy() {
        return new NashorsTooth();
    }
}
