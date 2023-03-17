import simulationManager.*;
import simulationManager.simulation.*;
import simulationManager.simulation.champions.*;
import simulationManager.simulation.items.*;
import simulationManager.simulation.runes.*;

import java.util.List;

import static simulationManager.simulation.AbilityType.*;
import static simulationManager.simulation.items.ItemList.*;

public class KaisaDpsExample {
    public static void calculateDps() {
        Champion kaisa = new Kaisa();
        Champion dummy = new Dummy(7000, 250, 200);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Rune[] runes = {
                new PressTheAttack(),
                new LegendAlacrity(10),
                new CoupDeGrace(),
                new EyeballCollection(10),
                new UltimateHunter(5),
                new Shards(1,0,1)
        };
        RunePage runePage = new RunePage(RunePath.precision, RunePath.domination);
        runePage.setRunes(runes);

        Item[] build = {
                new BerserkersGreaves(),
                new Riftmaker(),
                new GuinsoosRageblade(),
                new BladeOfTheRuinedKing()
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        kaisa.setRunePage(runePage);
        kaisa.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(kaisa);
        sm.setEnemy(dummy);

        sm.simulateDps(new AbilityType[] {q, auto, e, w});
    }


    public static void calculateDps2() {
        Champion kaisa = new Kaisa();
        Champion dummy = new Dummy(7000, 250, 200);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Rune[] runes = {
                new LethalTempo(),
                new CutDown(),
                new LegendAlacrity(10),
                new EyeballCollection(10),
                new Shards(1,0,1)
        };
        RunePage runePage = new RunePage(RunePath.precision, RunePath.domination);
        runePage.setRunes(runes);

        Item[] build = {
                new BerserkersGreaves(),
                new Riftmaker(),
                new LordDominiksRegards(),
                new GuinsoosRageblade(),
                new BladeOfTheRuinedKing()
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        kaisa.setRunePage(runePage);
        kaisa.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(kaisa);
        sm.setEnemy(dummy);

        sm.simulateDps(new AbilityType[] {q, auto, e, r});
    }


    public static void calculateBestBuild() {
        Champion kaisa = new Kaisa();
        Champion dummy = new Dummy(7500, 130, 100);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Rune[] runes = {
                new LethalTempo(),
                new LegendAlacrity(10),
                new EyeballCollection(10),
                new Shards(1,0,1)
        };
        RunePage runePage = new RunePage(RunePath.precision, RunePath.domination);
        runePage.setRunes(runes);

        Item[] permanentItems = {
                new BerserkersGreaves()
        };
        Item[] variableItems = {
                new KrakenSlayer(),
                new Riftmaker(),
                new GuinsoosRageblade(),
                new BladeOfTheRuinedKing(),
                new WitsEnd(),
                new NashorsTooth()
        };

        BuildTester bt = new BuildTester(4, 100000);
        bt.setRunePage(runePage);
        bt.setPermanentItems(List.of(permanentItems));
        bt.setVariableItems(List.of(variableItems));

        bt.sortBuilds(kaisa, dummy, new AbilityType[] {q,auto,e,w}, false);
    }


    public static void main(String[] args) {
        calculateDps();
        //calculateDps2();
        //calculateBestBuild();
    }
}
