import simulationManager.*;
import simulationManager.simulation.*;
import simulationManager.simulation.champions.*;
import simulationManager.simulation.runes.*;

import static simulationManager.simulation.AbilityType.*;
import static simulationManager.simulation.items.ItemList.*;

import java.util.List;

public class KaisaComboExample {
    public static void calculateBestBuild() {
        Champion kaisa = new Kaisa();
        Champion dummy = new Dummy(2500, 130, 100);

        kaisa.lvl = 13;
        kaisa.upgradeOrder = new AbilityType[] {q,w,e,q,q,r,q,e,q,e,r,e,e,w,w,r,w,w};

        Rune[] runes = {
                new FirstStrike(),
                new BiscuitDelivery(3),
                new AbsoluteFocus(),
                new GatheringStorm(3),
                new Shards(1,0,1)
        };
        RunePage runePage = new RunePage(RunePath.inspiration, RunePath.sorcery);
        runePage.setRunes(runes);

        Item[] permanentItems = {
                sorcerersShoes,
                ludensTempest,
                voidStaff
        };
        Item[] variableItems = {
                rabbadonsDeathcap
        };

        BuildTester bt = new BuildTester(4,100000);
        bt.setRunePage(runePage);
        bt.setPermanentItems(List.of(permanentItems));
        bt.setVariableItems(List.of(variableItems));

        AbilityType[] combo = {w,w};
        bt.sortBuilds(kaisa, dummy, combo, true);
    }

    public static void main(String[] args) {
        calculateBestBuild();
    }
}
