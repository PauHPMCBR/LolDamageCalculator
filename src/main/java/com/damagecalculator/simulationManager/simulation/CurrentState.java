package com.damagecalculator.simulationManager.simulation;

import com.damagecalculator.simulationManager.simulation.items.AbyssalMask;
import com.damagecalculator.simulationManager.simulation.items.BlackCleaver;
import com.damagecalculator.simulationManager.simulation.items.BloodlettersCurse;
import com.damagecalculator.simulationManager.simulation.items.Shadowflame;
import com.damagecalculator.simulationManager.simulation.runes.AxiomArcanist;
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

    public float damageMultiplier = 1;  //damage multipler that works with true dmg
    public float abilityDamageMultiplier = 1;

    public AbyssalMask abyssalMaskItem;
    public BloodlettersCurse bloodlettersCurseItem;
    public BlackCleaver cleaverItem;
    public Shadowflame shadowflameItem;
    public FirstStrike firstStrikeRune;
    public AxiomArcanist axiomArcanistRune;
    public boolean hasFirstStrike = false;
    public boolean hasCoupDeGrace = false;
    public boolean hasCutDown = false;
    public boolean hasLastStand = false; //unused atm

    public void startSimulation() {
        time = 0;
        damageMultiplier = 1;

        abilityDamageMultiplier = 1;

        abyssalMaskItem = null;
        bloodlettersCurseItem = null;
        cleaverItem = null;
        shadowflameItem = null;

        firstStrikeRune = null;
        axiomArcanistRune = null;

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
