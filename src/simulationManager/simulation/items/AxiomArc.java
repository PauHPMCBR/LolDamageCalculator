package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class AxiomArc extends Item {
    public static final String name = "Axiom Arc";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public AxiomArc() {
        super(name, type, cost);
        ad = 55;
        lethality = 18;
        ah = 25;
    }

    @Override
    public Item makeCopy() {
        return new AxiomArc();
    }
}
