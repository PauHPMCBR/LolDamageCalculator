package com.damagecalculator.simulationManager.simulation;

import com.damagecalculator.simulationManager.simulation.items.LudensTempest;
import com.damagecalculator.simulationManager.simulation.items.BlackCleaver;
import com.damagecalculator.simulationManager.simulation.items.Riftmaker;
import com.damagecalculator.simulationManager.simulation.runes.FirstStrike;


/**
 * Holds important variables that are globally important.
 * It is also the class used to communicate from outside to inside the simulationManager.simulation.
 */
public class CurrentState {
    public Champion champion;
    public Champion enemy;

    public Damage damage;

    public float time; //in secs

    public float damageMultiplier = 1;      //damage multipler that doesn't work with true dmg
    public float damageTrueMultiplier = 1;  //damage multipler that works with true dmg
    public float navoriPercent = 1;
    public float liandryPercent = 1;

    public Riftmaker riftmakerItem; //pointer to the riftmaker item, so the extra damage can be given to the item
    public BlackCleaver cleaverItem;
    public LudensTempest ludensItem;
    public FirstStrike firstStrikeRune;
    public boolean hasFirstStrike = false;
    public boolean hasCoupDeGrace = false;
    public boolean hasCutDown = false;
    public boolean hasLastStand = false; //unused atm

    public void startSimulation() {
        time = 0;
        damageMultiplier = 1;
        damageTrueMultiplier = 1;

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

        //initialize items and runes
        champion.initializeItems();
        champion.initializeRunes();
        enemy.initializeItems();
        enemy.initializeRunes();
        for (Rune rune : champion.runes.runeList) rune.specialStats();
        for (Item item : champion.allItems) item.specialStats();
        for (Rune rune : enemy.runes.runeList) rune.specialStats();
        for (Item item : enemy.allItems) item.specialStats();

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
