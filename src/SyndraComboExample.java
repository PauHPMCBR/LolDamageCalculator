import simulationManager.*;
import simulationManager.simulation.*;
import simulationManager.simulation.champions.*;
import simulationManager.simulation.items.*;
import simulationManager.simulation.runes.*;

import static simulationManager.simulation.AbilityType.*;
import static simulationManager.simulation.items.ItemList.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SyndraComboExample {

    public static void calculateComboDamage() {
        Champion syndra = new Syndra(120);
        Champion dummy = new Dummy(2100, 100, 100);

        syndra.lvl = 13;
        syndra.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};

        Rune[] runes = {
                new FirstStrike(),
                new BiscuitDelivery(3),
                new EyeballCollection(6),
                new UltimateHunter(5)
        };
        RunePage runePage = new RunePage(RunePath.inspiration, RunePath.domination);
        runePage.setRunes(runes);


        Item[] build = {
                sorcerersShoes,
                ludensTempest,
                needlesslyLargeRod,
                hextechAlternator
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        syndra.setInventory(inventory);
        syndra.setRunePage(runePage);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(syndra);
        sm.setEnemy(dummy);

        AbilityType[] combo = {q,e,w,q,r};
        sm.simulateCombo(combo);
    }


    public static void calculateBestBuild() {
        Champion syndra = new Syndra(120);
        Champion dummy = new Dummy(2500, 130, 60);

        syndra.lvl = 13;
        syndra.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,w,q,w,r,w,w,e,e,r,e,e};

        Rune[] runes = {
                new Electrocute(),
                new EyeballCollection(10),
                new AbsoluteFocus(),
                new GatheringStorm(2)
        };
        RunePage runePage = new RunePage(RunePath.domination, RunePath.sorcery);
        runePage.setRunes(runes);

        Item[] permanentItems = {
                new SorcerersShoes(),
                new LudensTempest()
        };
        Item[] variableItems = {
                new Shadowflame(),
                new VoidStaff(),
                new RabbadonsDeathcap()
        };

        BuildTester bt = new BuildTester(6, 4000);
        bt.setRunePage(runePage);
        //bt.setPermanentItems(List.of(permanentItems));
        //bt.setVariableItems(List.of(variableItems));
        bt.setPermanentItems(new ArrayList<>());
        bt.setVariableItems(allMagicDmg); //check for ALL possible builds (that give ap / magic pen)

        AbilityType[] combo = {q,e,w,q,r};
        bt.sortBuilds(syndra, dummy, combo, true);
    }


    public static void main(String[] args) {
        //calculateComboDamage();
        calculateBestBuild();
    }
}
