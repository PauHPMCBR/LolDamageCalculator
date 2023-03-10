import simulationManager.BuildTester;
import simulationManager.SimulationManager;
import simulationManager.simulation.*;

import java.util.List;

import static simulationManager.simulation.AbilityType.*;

public class KaisaDpsExample {
    public static void calculateDps() {
        Champion kaisa = ChampionInstances.createKaisa();
        Champion dummy = ChampionInstances.createDummy(7000, 250, 200);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Item[] runes = {
                Runes.lethalTempo,
                Runes.legendAlacrity,
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
                LegendaryItems.witsEnd,
                LegendaryItems.bladeOfTheRuinedKing,
                LegendaryItems.blackCleaver
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        kaisa.setRunes(List.of(runes));
        kaisa.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(kaisa);
        sm.setEnemy(dummy);

        sm.simulateDps(new AbilityType[] {q, auto, e, w});
    }


    public static void calculateDps2() {
        Champion kaisa = ChampionInstances.createKaisa();
        Champion dummy = ChampionInstances.createDummy(7000, 250, 200);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Item[] runes = {
                Runes.lethalTempo,
                Runes.cutDown,
                Runes.legendAlacrity,
                Runes.eyeballCollection,
                Runes.shards
        };
        Runes.eyeballStacks = 10;
        Runes.shard1 = 1; //as
        Runes.shard2 = 0; //ad
        Runes.shard3 = 1; //armor

        Item[] build = {
                Items.berserkers,
                MythicItems.riftmaker,
                LegendaryItems.lordDominiksRegards,
                LegendaryItems.guinsoosRageblade,
                LegendaryItems.bladeOfTheRuinedKing
        };
        Inventory inventory = new Inventory();
        inventory.addAll(build);

        kaisa.setRunes(List.of(runes));
        kaisa.setInventory(inventory);

        SimulationManager sm = new SimulationManager();
        sm.setChampion(kaisa);
        sm.setEnemy(dummy);

        sm.simulateDps(new AbilityType[] {q, auto, e, r});
    }


    public static void calculateBestBuild() {
        Champion kaisa = ChampionInstances.createKaisa();
        Champion dummy = ChampionInstances.createDummy(7500, 130, 100);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Item[] runes = {
                Runes.lethalTempo,
                Runes.legendAlacrity,
                Runes.eyeballCollection,
                Runes.shards
        };
        Runes.eyeballStacks = 10;
        Runes.shard1 = 1; //as
        Runes.shard2 = 0; //ad
        Runes.shard3 = 1; //armor

        Item[] permanentItems = {
                Items.berserkers
        };
        Item[] variableItems = {
                MythicItems.krakenSlayer,
                MythicItems.riftmaker,
                LegendaryItems.guinsoosRageblade,
                LegendaryItems.bladeOfTheRuinedKing,
                LegendaryItems.witsEnd,
                LegendaryItems.nashorsTooth
        };

        BuildTester bt = new BuildTester();
        bt.setMaxItems(4);
        bt.setRunes(List.of(runes));
        bt.setPermanentItems(List.of(permanentItems));
        bt.setVariableItems(List.of(variableItems));

        bt.sortBuilds(kaisa, dummy, new AbilityType[] {q,auto,e,w}, false);
    }


    public static void main(String[] args) {
        //calculateDps();
        //calculateDps2();
        calculateBestBuild();
    }
}
