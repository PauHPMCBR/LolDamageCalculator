package com.damagecalculator.simulationManager.simulation;

public abstract class Rune {
    public Champion owner;
    public CurrentState cs;

    public final String name;
    public final RunePath path;
    public final int column; //(0 is for keystone), yeah, it's swapped (row - column)
    public final int row;

    public String extraVariableName = null;

    public boolean is_hidden = false; //if the rune is hidden, it won't be displayed in results messages

    public float rune_cooldown;

    public float lastUsed;
    public float damageDealt;
    public int getDamageDealt() {
        return Math.round(damageDealt);
    }

    public final void putOnCooldown() {
        lastUsed = cs.time;
    }
    public final boolean canUse() {
        return cs.time > lastUsed + rune_cooldown;
    }


    public void specialStats() {}
    public void onHit() {} //will get called by autos
    public void extraDmg() {} //will get called by abilities


    public Rune(String name, RunePath path, int column, int row) {
        damageDealt = 0;
        lastUsed = -500;

        this.name = name;
        this.path = path;
        this.column = column;
        this.row = row;
    }


    @Override
    public boolean equals(Object i) {
        if (!(i instanceof Rune)) return false;
        return ((Rune) i).name.equals(this.name);
    }

    public abstract Rune makeCopy();
}
