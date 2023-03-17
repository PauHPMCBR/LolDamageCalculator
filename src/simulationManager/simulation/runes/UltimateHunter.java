package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class UltimateHunter extends Rune {
    public static final String name = "Ultimate Hunter";
    public static final RunePath path = RunePath.domination;
    public static final int column = 3;
    public static final int row = 3;

    int stacks;

    public UltimateHunter(int stacks) {
        super(name, path, column, row);

        this.stacks = Math.min(stacks, 5);
    }

    public void specialStats() {
        cs.champion.ULTIMATE_HASTE += 6 + 5*stacks;
    }

    @Override
    public Rune makeCopy() {
        return new UltimateHunter(stacks);
    }
}