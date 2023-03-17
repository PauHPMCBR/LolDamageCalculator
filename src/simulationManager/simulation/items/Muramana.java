package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Muramana extends Item {
    public static final String name = "Muramana";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2900;

    public Muramana() {
        super(name, type, cost);
        ad = 35;
        ah = 15;
        mana = 860;
    }

    public void specialStats() {
        owner.BONUS_AD += owner.MANA * 0.025;
    }

    public void extraDmg() {
        float percent = 0.035f;
        if (owner.is_ranged) percent = 0.027f;
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg,
                (float) (owner.MANA * percent + owner.BONUS_AD * 0.06), 1);
    }

    public void onHit() {
        damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, 0.015f * owner.MANA, 1);
    }

    @Override
    public Item makeCopy() {
        return new Muramana();
    }
}
