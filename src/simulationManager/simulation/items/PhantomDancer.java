package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class PhantomDancer extends Item {
    public static final String name = "Phantom Dancer";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    int autos;

    public PhantomDancer() {
        super(name, type, cost);
        ad = 20;
        as = 25;
        crit = 20;
        ms = 7;

        autos = 0;
    }

    public void onHit() {
        ++autos;
        if (autos == 4) {
            owner.BONUS_AS += 30;
        }
    }

    @Override
    public Item makeCopy() {
        return new PhantomDancer();
    }
}
