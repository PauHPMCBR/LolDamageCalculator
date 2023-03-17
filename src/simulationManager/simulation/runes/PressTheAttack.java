package simulationManager.simulation.runes;

import simulationManager.simulation.Rune;
import simulationManager.simulation.RunePath;

public class PressTheAttack extends Rune {
    public static final String name = "Press the Attack";
    public static final RunePath path = RunePath.precision;
    public static final int column = 0;
    public static final int row = 0;

    boolean isActive;
    float timeActivated;
    int autos;

    public PressTheAttack() {
        super(name, path, column, row);

        isActive = false;
        autos = 0;
    }

    public void onHit() {
        if (isActive) {
            if (cs.time >= timeActivated + 6) {
                float increasedDmg = (float) (1.08 + 0.04 * (cs.champion.lvl-1)/17);
                cs.damageMultiplier /= increasedDmg;

                isActive = false;
            }
        }
        else {
            ++autos;
            if (autos == 3) {
                damageDealt += cs.damage.applyDamage(cs.champion.getAdaptive(),
                        40 + ((float)(140*cs.champion.lvl-1))/17, 1);
                float increasedDmg = (float) (1.08 + 0.04 * (cs.champion.lvl-1)/17);
                cs.damageMultiplier *= increasedDmg;

                isActive = true;
                timeActivated = cs.time;
                autos = 0;
            }
        }
    }

    @Override
    public Rune makeCopy() {
        return new PressTheAttack();
    }
}