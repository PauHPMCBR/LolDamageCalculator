import simulationManager.*;
import simulationManager.simulation.*;
import simulationManager.simulation.champions.*;
import simulationManager.simulation.runes.EyeballCollection;
import simulationManager.simulation.runes.PressTheAttack;
import simulationManager.simulation.runes.Shards;

import static simulationManager.simulation.AbilityType.*;
import static simulationManager.simulation.items.ItemList.*;

import java.util.List;

public class LucianDpsExample {
    public static void calculateDps() {
        Champion lucian = new Lucian();
        Champion dummy = new Dummy(7000, 250, 250);

        lucian.lvl = 13;
        lucian.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Rune[] runes = {
                new PressTheAttack(),
                new EyeballCollection(5),
                new Shards(1,0,1)
        };
        RunePage runePage = new RunePage(RunePath.precision, RunePath.domination);
        runePage.setRunes(runes);

        Item[] build = {
                berserkersGreaves,
                krakenSlayer,
                navoriQuickblades,
                bloodthister
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        lucian.setRunePage(runePage);
        lucian.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(lucian);
        sm.setEnemy(dummy);

        sm.useAutosBetweenAbilities = true;
        sm.simulateDps(new AbilityType[] {auto, e, q, w}); //trying without r!!
    }



    public static void calculateBestBuild() {
        Champion lucian = new Lucian();
        Champion dummy = new Dummy(15000, 230, 200);

        lucian.lvl = 13;
        lucian.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Rune[] runes = {
                new PressTheAttack(),
                new EyeballCollection(5),
                new Shards(1,0,1)
        };
        RunePage runePage = new RunePage(RunePath.precision, RunePath.domination);
        runePage.setRunes(runes);

        Item[] permanentItems = {
        };
        Item[] variableItems = {
                krakenSlayer,
                trinityForce,
                muramana,
                guinsoosRageblade,
                bladeOfTheRuinedKing,
                witsEnd,
                essenceReaver,
                navoriQuickblades,
                infinityEdge,
                lordDominiksRegards,
                blackCleaver,
                theCollector
        };

        BuildTester bt = new BuildTester(6, 10000);
        bt.setRunePage(runePage);
        bt.setPermanentItems(List.of(permanentItems));
        bt.setVariableItems(List.of(variableItems));

        bt.useAutosBetweenAbilities = true;
        bt.sortBuilds(lucian, dummy, new AbilityType[] {auto,e,q,w}, false);
    }


    public static void main(String[] args) {
        //calculateDps();
        calculateBestBuild();
    }
}
