package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SpectralSickle extends Item {
    public static final String name = "Spectral Sickle";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 400;

    public SpectralSickle() {
        super(name, type, cost);
        ad = 5;
        hp = 10;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new SpectralSickle();
    }
}
