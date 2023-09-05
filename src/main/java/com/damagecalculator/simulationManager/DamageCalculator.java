package com.damagecalculator.simulationManager;

import com.damagecalculator.GlobalVariables;
import com.damagecalculator.simulationManager.simulation.*;
import com.damagecalculator.simulationManager.simulation.items.DemonicEmbrace;
import com.damagecalculator.simulationManager.simulation.items.ItemList;
import com.damagecalculator.simulationManager.simulation.items.LiandrysAnguish;

import java.util.*;

/**
 * Class that will make the champion use abilities and auto attacks and can measure:
 * - DPS (calculated in time taken to kill the enemy)
 * - Damage dealt by a combo
 */
public class DamageCalculator {
    CurrentState cs;

    /**
     * Class made to sort expiring abilities by time
     */
    static class AbilityEvent {
        float time;
        Ability ability;
        public AbilityEvent(float time, Ability ability) {
            this.time = time;
            this.ability = ability;
        }
    }
    static class AbilityEventComparator implements Comparator<AbilityEvent> {
        public int compare(AbilityEvent a1, AbilityEvent a2) {
            return (a1.time <= a2.time) ? 1 : -1;
        }
    }

    private PriorityQueue<Float> eventTimes;
    private PriorityQueue<AbilityEvent> expiringAbilities;
    List<AbilityType> comboDone; //saves the order of which abilities (and autos) have been used

    private void increaseTime(float amount) {
        if (amount < 0) return; //no back in time :)
        cs.time += amount;
        cs.champion.q.currentCooldown -= amount;
        cs.champion.w.currentCooldown -= amount;
        cs.champion.e.currentCooldown -= amount;
        cs.champion.r.currentCooldown -= amount;
        cs.champion.passive.currentCooldown -= amount;
        cs.champion.autoCd -= amount;
    }

    private static boolean canUse(Ability a) {
        return a.isUnlocked() && (a.getCooldown() != -1) && a.currentCooldown <= 0.05 && !a.active; //TODO the active part may cause problems later
    }

    private void useAbility(Ability a) {
        increaseTime(a.cast_time);

        cs.champion.useAbility(a);
        comboDone.add(a.type);

        eventTimes.add(cs.time + a.currentCooldown);
        if (a.getDuration() != -1) {
            expiringAbilities.add(new AbilityEvent(cs.time + a.getDuration(), a));
            a.active = true;
        }
    }
    private void useAuto() {
        increaseTime(cs.champion.getAttackWindupTime());
        cs.champion.autoAttack();
        comboDone.add(AbilityType.AUTO);
        eventTimes.add(cs.time + cs.champion.autoCd);
    }

    /**
     * Main function to calculate the time it takes to kill an enemy using everything, not accounting for mana limitations
     */
    public boolean useAutosBetweenAbilities = false;
    public float timeToKill(AbilityType[] abilityPriorities, boolean debug) { //debug will usually be false, otherwise it can fill the screen rather quickly
        eventTimes = new PriorityQueue<>();
        expiringAbilities = new PriorityQueue<>(new AbilityEventComparator());
        comboDone = new ArrayList<>();
        eventTimes.add(0f);

        while (cs.enemy.alive) { //simulationManager.simulation will stop when enemy dies

            //first, check if any ability has expired and call "onExpiring()"
            while(!expiringAbilities.isEmpty() && expiringAbilities.peek().time <= cs.time) {
                Ability a = expiringAbilities.remove().ability;
                a.onExpiring();
                a.active = false;
            }

            //then, check if any ability can be used and use it in order abilityPriorities, only one per "while" cycle
            boolean doneSomething = false;
            for (AbilityType a : abilityPriorities) {
                if (a == AbilityType.AUTO) {
                    if (cs.champion.autoCd <= 0.05) {
                        if (debug) System.out.println("Used a in time " + cs.time);
                        useAuto();

                        doneSomething = true;
                        break;
                    }
                }
                else {
                    if (canUse(cs.champion.getAbility(a))) {
                        if (debug) System.out.println("Used " + a + " in time " + cs.time);
                        useAbility(cs.champion.getAbility(a));

                        doneSomething = true;
                        break;
                    }
                }
            }

            if (!doneSomething) {
                //when everything that could be used in this time has been used, go to the next event time saved
                // (like auto refresh or ability coming back off cooldown)
                float newTime = eventTimes.remove();
                while (newTime < cs.time) {
                    newTime = eventTimes.remove();
                }
                increaseTime(newTime - cs.time);
            }

            if (useAutosBetweenAbilities) {
                if (comboDone.get(comboDone.size() -1) != AbilityType.AUTO)
                    while (cs.champion.autoCd > 0.05) { //?
                        float newTime = eventTimes.remove();
                        increaseTime(newTime - cs.time);
                    }
            }

        }

        return cs.time;
    }

    /**
     * Main function to apply a specific combo, not accounting for mana limitations
     */
    boolean hasBurnItem;
    public void applyCombo(AbilityType[] combo) {
        eventTimes = new PriorityQueue<>();
        expiringAbilities = new PriorityQueue<>(new AbilityEventComparator());
        comboDone = new Vector<>();
        for (AbilityType abilityType : combo) {
            if (abilityType == AbilityType.AUTO) {
                increaseTime(cs.champion.autoCd);
                useAuto();
                cs.champion.autoAttack();
            }
            else {
                Ability a = cs.champion.getAbility(abilityType);
                if (a.isUnlocked()) {
                    increaseTime(a.currentCooldown);
                    useAbility(a);
                }
                else System.out.println("Can't use " + a.type + " because it's not unlocked!");
            }
        }

        while(!expiringAbilities.isEmpty()) { //apply all expiring abilities before ending, but will not count towards time taken
            Ability a = expiringAbilities.remove().ability;
            a.onExpiring();
            a.active = false;
        }

        if (GlobalVariables.extraBurnTime != 0) {
            hasBurnItem = false;
            for (Item i : cs.champion.allItems) {
                if (i.equals(ItemList.liandrysAnguish) || i.equals(ItemList.demonicEmbrace)) {
                    if (!hasBurnItem) {
                        hasBurnItem = true;
                        cs.time += GlobalVariables.extraBurnTime; //extra burn time
                    }
                    i.extraDmg();
                }
            }
        }
    }

    public DamageCalculator(CurrentState cs) {
        this.cs = cs;
    }
}
