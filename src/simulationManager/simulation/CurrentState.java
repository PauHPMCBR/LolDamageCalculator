package simulationManager.simulation;

/**
 * Holds important variables that are globally important.
 * It is also the class used to communicate from outside to inside the simulationManager.simulation.
 */
public class CurrentState {
    public Champion champion;
    public Champion enemy;

    public Damage damage;

    public float time; //in secs

    public float damageMultiplier = 1;
    public float ldrPercent = 1;
    public float navoriPercent = 1;
    public float liandryPercent = 1;

    public Item riftmakerItem; //pointer to the riftmaker item, so the extra damage can be given to the item
    public Item cleaverItem;
    public Item ludensItem;
    public boolean hasFirstStrike = false;
    public boolean hasCoupDeGrace = false;
    public boolean hasCutDown = false;
    public boolean hasLastStand = false; //unused atm

    public void startSimulation() {
        time = 0;
        damageMultiplier = 1;

        ldrPercent = 1;
        navoriPercent = 1;
        liandryPercent = 1;

        riftmakerItem = null;
        cleaverItem = null;
        ludensItem = null;

        hasLastStand = false;
        hasCutDown = false;
        hasCoupDeGrace = false;

        //initialize champions
        champion.initializeValues(this);
        enemy.initializeValues(this);

        //initialize items
        champion.initializeItems();
        enemy.initializeItems();
        for (Item item : champion.allItems)
            item.specialStats();
        for (Item item : enemy.allItems)
            item.specialStats();

        //initialize abilities
        for (Ability a : champion.allAbilities)
            a.startingCalculations();
        for (Ability a : enemy.allAbilities)
            a.startingCalculations();

        //lastly set their starting hp
        champion.HP = champion.getMaxHP();
        enemy.HP = enemy.getMaxHP();


        damage = new Damage(this);
        time = 0;
    }
}
