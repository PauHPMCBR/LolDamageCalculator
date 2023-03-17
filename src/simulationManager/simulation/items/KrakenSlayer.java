package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class KrakenSlayer extends Item {
    public static final String name = "Kraken Slayer";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3400;

    int autos;

    public KrakenSlayer() {
        super(name, type, cost);
        ad = 65;
        as = 25;
        crit = 20;

        autos = 0;
    }

    public void onHit() {
        ++autos;
        if (autos % 3 == 0)
            damageDealt += cs.damage.applyDamage(DamageType.trueDmg, (float) (owner.getAD() * 0.4 + 50), 1);
    }
    public void applyMythicPassive() {
        owner.BONUS_AS += 10 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new KrakenSlayer();
    }
}
