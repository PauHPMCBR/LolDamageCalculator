package simulationManager.simulation.runes;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class EyeballCollection extends Rune {
    public static final String name = "Eyeball Collection";
    public static final RunePath path = RunePath.domination;
    public static final int column = 2;
    public static final int row = 2;

    int stacks;

    public EyeballCollection(int stacks) {
        super(name, path, column, row);

        this.stacks = Math.min(stacks, 10);
    }

    public void specialStats() {
        if (cs.champion.getAdaptive().equals(DamageType.physicalDmg)) {
            cs.champion.BONUS_AD += 1.2 * stacks;
            if (stacks == 10) cs.champion.BONUS_AD += 6;
        } else {
            cs.champion.AP += 2 * stacks;
            if (stacks == 10) cs.champion.AP += 10;
        }
    }

    @Override
    public Rune makeCopy() {
        return new EyeballCollection(stacks);
    }
}
