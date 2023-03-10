import simulationManager.BuildTester;
import simulationManager.SimulationManager;
import simulationManager.simulation.*;
import simulationManager.simulation.AbilityType.*;

import java.util.List;

import static simulationManager.simulation.AbilityType.*;
import static simulationManager.simulation.AbilityType.w;

public class LucianDpsExample {
    public static void calculateDps() {
        Champion lucian = ChampionInstances.createLucian();
        Champion dummy = ChampionInstances.createDummy(7000, 250, 250);

        lucian.lvl = 13;
        lucian.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Item[] runes = {
                Runes.pressTheAttack,
                Runes.eyeballCollection,
                Runes.shards
        };
        Runes.eyeballStacks = 10;
        Runes.shard1 = 1; //as
        Runes.shard2 = 0; //ad
        Runes.shard3 = 1; //armor

        Item[] build = {
                Items.berserkers,
                MythicItems.krakenSlayer,
                LegendaryItems.navoriQuickblades,
                LegendaryItems.bloodthister
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        lucian.setRunes(List.of(runes));
        lucian.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(lucian);
        sm.setEnemy(dummy);

        sm.useAutosBetweenAbilities = true;
        sm.simulateDps(new AbilityType[] {auto, e, q, w}); //trying without r!!
    }



    public static void calculateBestBuild() {
        Champion lucian = ChampionInstances.createLucian();
        Champion dummy = ChampionInstances.createDummy(15000, 230, 200);

        lucian.lvl = 13;
        lucian.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Item[] runes = {
                Runes.pressTheAttack,
                Runes.eyeballCollection,
                Runes.shards
        };
        Runes.eyeballStacks = 10;
        Runes.shard1 = 1; //as
        Runes.shard2 = 0; //ad
        Runes.shard3 = 1; //armor

        Item[] permanentItems = {
        };
        Item[] variableItems = {
                MythicItems.krakenSlayer,
                MythicItems.trinityForce,
                LegendaryItems.muramana,
                LegendaryItems.guinsoosRageblade,
                LegendaryItems.bladeOfTheRuinedKing,
                LegendaryItems.witsEnd,
                LegendaryItems.essenceReaver,
                LegendaryItems.navoriQuickblades,
                LegendaryItems.infinityEdge,
                LegendaryItems.lordDominiksRegards,
                LegendaryItems.blackCleaver,
                LegendaryItems.collector
        };

        BuildTester bt = new BuildTester();
        bt.setMaxItems(6);
        bt.setRunes(List.of(runes));
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
