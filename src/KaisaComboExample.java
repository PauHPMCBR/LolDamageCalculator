import simulationManager.BuildTester;
import simulationManager.simulation.*;

import java.util.List;

import static simulationManager.simulation.AbilityType.*;
import static simulationManager.simulation.AbilityType.w;

public class KaisaComboExample {
    public static void calculateBestBuild() {
        Champion kaisa = ChampionInstances.createKaisa();
        Champion dummy = ChampionInstances.createDummy(2500, 130, 100);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Item[] runes = {
                Runes.firstStrike,
                Runes.biscuitDelivery,
                Runes.absoluteFocus,
                Runes.gatheringStorm,
                Runes.shards
        };
        Runes.gatheringStacks = 3;
        Runes.shard1 = 1; //as
        Runes.shard2 = 0; //ad
        Runes.shard3 = 1; //armor

        Item[] permanentItems = {
                Items.sorcerers,
                MythicItems.ludensTempest,
                LegendaryItems.voidStaff
        };
        Item[] variableItems = {
                LegendaryItems.rabbadonsDeathcap
        };

        BuildTester bt = new BuildTester();
        bt.setMaxItems(4);
        bt.setRunes(List.of(runes));
        bt.setPermanentItems(List.of(permanentItems));
        bt.setVariableItems(List.of(variableItems));

        AbilityType[] combo = {w,w};
        bt.sortBuilds(kaisa, dummy, combo, true);
    }

    public static void main(String[] args) {
        calculateBestBuild();
    }
}
