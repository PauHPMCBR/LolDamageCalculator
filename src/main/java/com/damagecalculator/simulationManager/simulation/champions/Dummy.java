package com.damagecalculator.simulationManager.simulation.champions;

import com.damagecalculator.simulationManager.simulation.*;

public class Dummy extends Champion {
    public static final String name = "Dummy";

    int dummyHP, dummyArmor, dummyMr;

    public Dummy(int dummyHP, int dummyArmor, int dummyMr) {
        super (name,
                Math.min(2000, dummyHP),
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                0f,
                false);
        this.dummyHP = dummyHP;
        this.dummyArmor = dummyArmor;
        this.dummyMr = dummyMr;

        lvl = 1;

        Item dummyStats = new DummyStats(Math.max(0, dummyHP - 2000), dummyArmor, dummyMr);
        dummyStats.is_hidden = true;
        addUniqueItem(dummyStats);
    }

    @Override
    public Champion makeCopy() {
        return new Dummy(dummyHP, dummyArmor, dummyMr);
    }




    public static class DummyStats extends Item {
        public static final String name = "_dummy stats";
        public static final ItemType type = ItemType.unique;
        public static final int cost = 0;

        public DummyStats(int hp, int armor, int mr) {
            super(name, type, cost);
            this.hp = hp;
            this.armor = armor;
            this.mr = mr;
            is_hidden = true;
        }

        @Override
        public Item makeCopy() {
            return new DummyStats(hp, armor, mr);
        }
    }
}
