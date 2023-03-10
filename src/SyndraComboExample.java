import simulationManager.BuildTester;
import simulationManager.SimulationManager;
import simulationManager.simulation.*;

import java.util.List;

import static simulationManager.simulation.AbilityType.*;

public class SyndraComboExample {

    public static void calculateComboDamage() {
        Champion syndra = ChampionInstances.createSyndra(120);
        Champion dummy = ChampionInstances.createDummy(2100, 100, 100);

        syndra.lvl = 13;
        syndra.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};

        Item[] runes = {
                Runes.electrocute,
                Runes.eyeballCollection,
                Runes.absoluteFocus,
                Runes.gatheringStorm
        };
        Runes.gatheringStacks = 2;
        Runes.eyeballStacks = 10;



        Item[] build = {
                Items.sorcerers,
                MythicItems.ludensTempest,
                ComponentItems.needlesslyLargeRod,
                ComponentItems.hextechAlternator
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        syndra.setRunes(List.of(runes));
        syndra.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(syndra);
        sm.setEnemy(dummy);

        AbilityType[] combo = {q,e,w,q,r};
        sm.simulateCombo(combo);
    }


    public static void calculateBestBuild() {
        Champion syndra = ChampionInstances.createSyndra(120);
        Champion dummy = ChampionInstances.createDummy(2500, 130, 60);

        syndra.lvl = 13;
        syndra.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};

        Item[] runes = {
                Runes.electrocute,
                Runes.eyeballCollection,
                Runes.absoluteFocus,
                Runes.gatheringStorm
        };
        Runes.gatheringStacks = 2;
        Runes.eyeballStacks = 10;

        Item[] permanentItems = {
                Items.sorcerers,
                MythicItems.ludensTempest
        };
        Item[] variableItems = {
                LegendaryItems.shadowflame,
                LegendaryItems.voidStaff,
                LegendaryItems.rabbadonsDeathcap
        };

        BuildTester bt = new BuildTester();
        bt.setMaxItems(3);
        bt.setRunes(List.of(runes));
        bt.setPermanentItems(List.of(permanentItems));
        bt.setVariableItems(List.of(variableItems));

        AbilityType[] combo = {q,e,w,q,r};
        bt.sortBuilds(syndra, dummy, combo, true);
    }


    public static void main(String[] args) {
        calculateComboDamage();
        //calculateBestBuild();
    }
}
