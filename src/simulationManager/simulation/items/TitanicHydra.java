package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class TitanicHydra extends Item {
    public static final String name = "Titanic Hydra";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3300;


    public TitanicHydra() {
        super(name, type, cost);
    }

    public void specialStats() {
        owner.BONUS_AD += owner.BONUS_HP * 0.02;
    }

    public void onHit() {
        float dmg = (float) (4 + 0.015 * owner.getMaxHP());
        if (owner.is_ranged) dmg *= 0.75;
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, dmg, 1);
    }

    @Override
    public Item makeCopy() {
        return new TitanicHydra();
    }
}
