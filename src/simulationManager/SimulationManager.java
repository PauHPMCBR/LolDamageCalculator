package simulationManager;

import simulationManager.simulation.*;

public class SimulationManager {
    CurrentState cs;
    DamageCalculator dmgCalc;

    /**
     * Set both of these before starting a simulation. Keep in mind a COPY is created, so any modifications on the original will make no effect
     */
    public void setChampion(Champion c) {
        cs.champion = new Champion(c);
    }
    public void setEnemy(Champion c) {
        cs.enemy = new Champion(c);
    }

    int startingHP, startingArmor, startingMR; //used for dummy stats printing, as some effects may alter its stats (like abyssal mask or black cleaver)
    private void startSimulation() {
        dmgCalc = new DamageCalculator(cs);

        cs.startSimulation();
        startingHP = (int) cs.enemy.getMaxHP();
        startingArmor = (int) cs.enemy.ARMOR;
        startingMR = (int) cs.enemy.MAGIC_RESIST;
    }

    /**
     * Function that will call the damage calculator and print the results if asked for. Default print option is true
     */
    public float simulateCombo(AbilityType[] combo, boolean print) {
        startSimulation();
        dmgCalc.applyCombo(combo);

        if (print) {
            Printer.printChampionStats(cs.champion, true, false);
            Printer.printDummyStats(cs.enemy.getName(), startingHP, startingArmor, startingMR);
            Printer.printSeparator();
            Printer.printDamages(cs.champion, cs.damage);

            System.out.println();
            System.out.println();
        }

        return cs.damage.getTotalDamage();
    }
    public float simulateCombo(AbilityType[] combo) {
        return simulateCombo(combo, true);
    }

    /**
     * Function that will call the damage calculator and print the results if asked for. Default print option is true
     */
    public boolean useAutosBetweenAbilities = false;
    public float simulateDps(AbilityType[] abilityPriorities, boolean print) {
        startSimulation();

        dmgCalc.useAutosBetweenAbilities = useAutosBetweenAbilities;
        float time = dmgCalc.timeToKill(abilityPriorities, false);

        if (print) {
            Printer.printChampionStats(cs.champion, true, false);
            Printer.printDummyStats(cs.enemy.getName(), startingHP, startingArmor, startingMR);
            Printer.printSeparator();
            Printer.printTimeTaken(time);
            AbilityType[] combo = dmgCalc.comboDone.toArray(new AbilityType[0]);
            Printer.printCombo(combo);
            Printer.printSeparator();
            Printer.printDamages(cs.champion, cs.damage);

            System.out.println();
            System.out.println();
        }

        return time;
    }
    public float simulateDps(AbilityType[] abilityPriorities) {
        return simulateDps(abilityPriorities, true);
    }

    public SimulationManager() {
        cs = new CurrentState();
        Items.initializeItems();
    }
}
