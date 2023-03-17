package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class LethalTempo extends Rune {
    public static final String name = "Lethal Tempo";
    public static final RunePath path = RunePath.precision;
    public static final int column = 0;
    public static final int row = 1;

    int autos;

    public LethalTempo() {
        super(name, path, column, row);

        autos = 0;
    }

    public void onHit() {
        if (autos == 6) return;
        ++autos;
        int bonus_as = Math.min(15, cs.champion.lvl)/3;
        if (!cs.champion.is_ranged) bonus_as *= 2;
        else if (cs.champion.lvl >= 15) --bonus_as;
        bonus_as += 5;
        cs.champion.BONUS_AS += bonus_as;
        if (autos == 6) cs.champion.max_as = 10;
    }

    @Override
    public Rune makeCopy() {
        return new LethalTempo();
    }
}