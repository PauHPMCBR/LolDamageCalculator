package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SpellthiefsEdge extends Item {
    public static final String name = "Spellthief's Edge";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 400;

    public SpellthiefsEdge() {
        super(name, type, cost);
        ap = 8;
        hp = 10;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new SpellthiefsEdge();
    }
}
