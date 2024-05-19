package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

public class JackOfAllTrades extends Rune {
    public static final String name = "Jack of All Trades";
    public static final RunePath path = RunePath.Inspiration;
    public static final int column = 3;
    public static final int row = 2;

    public JackOfAllTrades() {
        super(name, path, column, row);
    }

    public void specialStats() { //lol
        int uniqueStats = 0;
        boolean has_ad = false, has_ap = false, has_as = false, has_ah = false, has_hp = false, has_armor = false,
                has_mr = false, has_mana = false, has_crit = false, has_mana_regen = false, has_hp_regen = false,
                has_lethality = false, has_armor_pen = false, has_magic_pen = false, has_percent_magic_pen = false,
                has_lifesteal = false, has_hsp = false, has_ms = false, has_percent_ms = false;
        for (Item item : owner.allItems) {
            if (item.ad != 0) has_ad = true;
            if (item.ap != 0) has_ap = true;
            if (item.as != 0) has_as = true;
            if (item.ah != 0) has_ah = true;
            if (item.hp != 0) has_hp = true;
            if (item.armor != 0) has_armor = true;
            if (item.mr != 0) has_mr = true;
            if (item.mana != 0) has_mana = true;
            if (item.crit != 0) has_crit = true;
            if (item.mana_regen != 0) has_mana_regen = true;
            if (item.hp_regen != 0) has_hp_regen = true;
            if (item.lethality != 0) has_lethality = true;
            if (item.armor_pen != 0) has_armor_pen = true;
            if (item.magic_pen != 0) has_magic_pen = true;
            if (item.percent_magic_pen != 0) has_percent_magic_pen = true;
            if (item.lifesteal != 0) has_lifesteal = true;
            if (item.hsp != 0) has_hsp = true;
            if (item.ms != 0) has_ms = true;
            if (item.percent_ms != 0) has_percent_ms = true;
        }

        if (has_ad) ++uniqueStats;
        if (has_ap) ++uniqueStats;
        if (has_as) ++uniqueStats;
        if (has_ah) ++uniqueStats;
        if (has_hp) ++uniqueStats;
        if (has_armor) ++uniqueStats;
        if (has_mr) ++uniqueStats;
        if (has_mana) ++uniqueStats;
        if (has_crit) ++uniqueStats;
        if (has_mana_regen) ++uniqueStats;
        if (has_hp_regen) ++uniqueStats;
        if (has_lethality) ++uniqueStats;
        if (has_armor_pen) ++uniqueStats;
        if (has_magic_pen) ++uniqueStats;
        if (has_percent_magic_pen) ++uniqueStats;
        if (has_lifesteal) ++uniqueStats;
        if (has_hsp) ++uniqueStats;
        if (has_ms) ++uniqueStats;
        if (has_percent_ms) ++uniqueStats;

        owner.AH += uniqueStats;
        if (uniqueStats >= 5) {
            if (owner.getAdaptive() == DamageType.physicalDmg) owner.BONUS_AD += 6;
            else owner.AP += 10;
        }
        if (uniqueStats >= 10) {
            if (owner.getAdaptive() == DamageType.physicalDmg) owner.BONUS_AD += 9;
            else owner.AP += 15;
        }
    }

    @Override
    public Rune makeCopy() {
        return new JackOfAllTrades();
    }
}