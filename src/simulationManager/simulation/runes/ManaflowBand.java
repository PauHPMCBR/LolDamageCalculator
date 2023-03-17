package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class ManaflowBand extends Rune {
    public static final String name = "Manaflow Band";
    public static final RunePath path = RunePath.sorcery;
    public static final int column = 1;
    public static final int row = 1;

    int stacks;

    public ManaflowBand(int stacks) {
        super(name, path, column, row);

        this.stacks = Math.min(stacks, 10);
    }
    public ManaflowBand() {
        super(name, path, column, row);

        this.stacks = 10;
    }

    public void specialStats() {
        owner.MANA += 25 * stacks;
    }

    @Override
    public Rune makeCopy() {
        return new ManaflowBand(stacks);
    }
}
