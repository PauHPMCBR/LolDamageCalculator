package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class CutDown extends Rune {
    public static final String name = "Cut Down";
    public static final RunePath path = RunePath.precision;
    public static final int column = 3;
    public static final int row = 1;

    public CutDown() {
        super(name, path, column, row);
    }

    public void specialStats() {
        cs.hasCutDown = true;
    }

    @Override
    public Rune makeCopy() {
        return new CutDown();
    }
}
