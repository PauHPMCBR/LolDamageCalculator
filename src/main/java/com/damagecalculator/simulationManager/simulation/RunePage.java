package com.damagecalculator.simulationManager.simulation;

import com.damagecalculator.simulationManager.simulation.runes.Empty;

import java.util.ArrayList;
import java.util.List;

public class RunePage {
    public final RunePath primary;
    public final RunePath secondary;

    public Rune keystone;
    public final Rune[] primarySlots = new Rune[4];
    public final Rune[] secondarySlots = new Rune[2];
    public Rune shards;

    public List<Rune> runeList;

    void updateRuneList() {
        runeList = new ArrayList<>();
        if (keystone != null) runeList.add(keystone);
        for (Rune i : primarySlots) if (i != null) runeList.add(i);
        for (Rune i : secondarySlots) if (i != null) runeList.add(i);
        if (shards != null) runeList.add(shards);
    }

    boolean add(Rune r) {
        if (r instanceof Empty) return false;
        if (r.path == RunePath.Shards) {
            shards = r;
        }
        else if (r.column == 0) {
            if ((keystone == null || (keystone instanceof Empty)) && r.path == primary) keystone = r;
            else {
                System.out.println("Invalid keystone! " + keystone + " " + r);
                return false;
            }
        }
        else {
            if (r.path == primary) {
                if (primarySlots[r.column] != null) System.out.println("Overriting " + primarySlots[r.column].name);
                primarySlots[r.column] = r;
            }
            else if (r.path == secondary) {
                if (secondarySlots[0] == null) secondarySlots[0] = r;
                else {
                    if (secondarySlots[1] != null) System.out.println("All secondary rune slots are filled, overriting " + secondarySlots[1].name);
                    secondarySlots[1] = r;
                }
            }
            else {
                System.out.println("Rune parsed doesn't belong to any set rune path!");
                return false;
            }
        }
        updateRuneList(); //not optimal, but not the worst option
        return true;
    }

    public boolean setRunes(List<Rune> runes, boolean bypassCheck) {
        keystone = null;
        primarySlots[0] = null; primarySlots[1] = null; primarySlots[2] = null;
        secondarySlots[0] = null; secondarySlots[1] = null;
        shards = null;
        boolean allAdded = true;
        for (Rune r : runes) {
            if (!add(r.makeCopy())) allAdded = false;
        }

        if (bypassCheck) runeList = runes;

        return allAdded;
    }
    public boolean setRunes(Rune[] runes) {
        return setRunes(List.of(runes), false);
    }
    public boolean setRunes(List<Rune> runes) {
        return setRunes(runes, false);
    }


    public RunePage(RunePath primary, RunePath secondary) {
        runeList = new ArrayList<>();
        this.primary = primary;
        this.secondary = secondary;
        if (primary == secondary) System.out.println("Can't create rune page with same 2 paths!");
    }

    public RunePage(RunePage i) {
        if (i == null) {
            this.primary = null;
            this.secondary = null;
        }
        else {
            this.primary = i.primary;
            this.secondary = i.secondary;

            if (i.keystone != null) this.keystone = i.keystone.makeCopy();
            for (int j = 0; j < 4; ++j) if (i.primarySlots[j] != null) primarySlots[j] = i.primarySlots[j].makeCopy();
            for (int j = 0; j < 2; ++j) if (i.secondarySlots[j] != null) secondarySlots[j] = i.secondarySlots[j].makeCopy();

            if (i.shards != null) this.shards = i.shards.makeCopy();
        }

        updateRuneList();
    }
}
