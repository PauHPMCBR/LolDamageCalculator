package com.damagecalculator.displays;

import com.damagecalculator.MainApplication;
import com.damagecalculator.simulationManager.simulation.*;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayUtils {
    public static final String itemsFolder = "item_icons/";
    public static final String championsFolder = "champion_icons/";
    public static final String runesFolder = "rune_icons/";

    public static Node createSpacer() {
        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static void distribute(HBox hBox) {
        List<Node> childrenLol = hBox.getChildren();
        List<Node> children = new ArrayList<>(childrenLol);
        if (children.size() < 2) return;
        hBox.getChildren().clear();
        hBox.getChildren().add(children.get(0));
        for (int i = 1; i < children.size(); ++i) {
            hBox.getChildren().add(createSpacer());
            hBox.getChildren().add(children.get(i));
        }
    }

    static ColorAdjust desaturate;
    public static void desaturate(ImageView iv) {
        iv.setEffect(desaturate);
    }

    static final String cssDefault = """
                -fx-border-color: black;
                -fx-border-width: 2;
                """;
    public static HBox addBorder(ImageView iv) {
        HBox hBox = new HBox();
        hBox.setStyle(cssDefault);
        hBox.getChildren().add(iv);
        return hBox;
    }

    public static boolean containsSubsequence(final String sequence, final String subsequence) {
        if (subsequence.isEmpty()) return true;
        char[] seq = sequence.toCharArray();
        char[] sub = subsequence.toCharArray();
        int index = 0;
        for (char c : seq) {
            if (c == sub[index]) {
                ++index;
                if (index == sub.length) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getItemDescription(Item i) {
        String s = i.name.toUpperCase() + "  " + i.cost + "G\n";
        if (i.ad != 0) s = s.concat("" + i.ad + " Attack damage\n");
        if (i.ap != 0) s = s.concat("" + i.ap + " Ability power\n");
        if (i.as != 0) s = s.concat("" + i.as + "% Attack speed\n");
        if (i.ah != 0) s = s.concat("" + i.ah + " Ability haste\n");
        if (i.hp != 0) s = s.concat("" + i.hp + " Health\n");
        if (i.armor != 0) s = s.concat("" + i.armor + " Armor\n");
        if (i.mr != 0) s = s.concat("" + i.mr + " Magic resist\n");
        if (i.mana != 0) s = s.concat("" + i.mana + " Mana\n");
        if (i.crit != 0) s = s.concat("" + i.crit + "% Critical strike chance\n");
        if (i.mana_regen != 0) s = s.concat("" + i.mana_regen + "% Mana regen\n");
        if (i.hp_regen != 0) s = s.concat("" + i.hp_regen + "% Health regen\n");
        if (i.lethality != 0) s = s.concat("" + i.lethality + " Lethality\n");
        if (i.armor_pen != 0) s = s.concat("" + i.armor_pen + "% Armor penetration\n");
        if (i.magic_pen != 0) s = s.concat("" + i.magic_pen + " Magic penetration\n");
        if (i.percent_magic_pen != 0) s = s.concat("" + i.percent_magic_pen + "% Magic penetration\n");
        if (i.lifesteal != 0) s = s.concat("" + i.lifesteal + "% Lifesteal\n");
        if (i.hsp != 0) s = s.concat("" + i.hsp + "% Heal & shield power\n");
        if (i.ms != 0) s = s.concat("" + i.ms + " Movement speed\n");
        if (i.percent_ms != 0) s = s.concat("" + i.percent_ms + "% Movement speed\n");
        return s;
    }

    public static AbilityType[] getAbilityList(String s) {
        char[] chars = s.toCharArray();
        ArrayList<AbilityType> ans = new ArrayList<>();
        for (char c : chars) {
            if (c == 'q' || c == 'Q') ans.add(AbilityType.Q);
            else if (c == 'w' || c == 'W') ans.add(AbilityType.W);
            else if (c == 'e' || c == 'E') ans.add(AbilityType.E);
            else if (c == 'r' || c == 'R') ans.add(AbilityType.R);
            else if (c == 'a' || c == 'A') ans.add(AbilityType.AUTO);
        }
        AbilityType[] ret = new AbilityType[ans.size()];
        for (int i = 0; i < ans.size(); ++i) ret[i] = ans.get(i);
        return ret;
    }

    public static String getString(AbilityType[] abilityTypes) {
        return Arrays.toString(abilityTypes)
                .replace(" ", "")
                .replace("[", "")
                .replace("]","")
                .replace("AUTO","A");
    }

    public static String getItemFileName(Item i) {
        String s = i.name;
        s = s.replaceAll(" ", "_");
        s = s.replaceAll("'", "%27");
        s = s.replaceAll(",", "%2C");
        s = s + "_item.png";
        return s;
    }
    public static Image getEmptyItemImage() {
        return new Image(String.valueOf(MainApplication.class.getResource(itemsFolder + "Empty_item.png")));
    }
    public static Image getNotFoundItemImage() {
        return new Image(String.valueOf(MainApplication.class.getResource(itemsFolder + "Not_Found_item.png")));
    }
    public static Image getItemImage(Item i) {
        if (i == null) return getEmptyItemImage();

        URL u = MainApplication.class.getResource(itemsFolder + getItemFileName(i));
        if (u == null) u = MainApplication.class.getResource(itemsFolder + getItemFileName(i).replace("_item.png", ".png"));
        if (u == null) return getNotFoundItemImage();
        return new Image(String.valueOf(u));
    }


    public static String getRuneFileName(Rune r) {
        String s = r.name;
        s = s.replaceAll(" ", "_");
        s = s.replaceAll("'", "%27");
        s = s.replaceAll(",", "%2C");
        s = s.replaceAll(":", "-");
        s = s + "_rune.png";
        return s;
    }
    public static Image getRuneImage(Rune r) {
        URL u = MainApplication.class.getResource(runesFolder + getRuneFileName(r));
        return new Image(String.valueOf(u), 52, 52, false, false);
    }
    public static Image getRunePathImage(RunePath rp) {
        URL u = MainApplication.class.getResource(runesFolder + rp.name() + "_icon(1).png");
        return new Image(String.valueOf(u), 41, 41, false ,false);
    }

    public static String getChampionFileName(String s) {
        s = s.replaceAll(" ", "_");
        s = s.replaceAll("'", "%27");
        s = s.replaceAll(",", "%2C");
        s = s + "Square.png";
        return s;
    }
    public static Image getChampionIcon(Champion c) {
        URL u = MainApplication.class.getResource(championsFolder + getChampionFileName(c.name));
        if (u == null) {
            u = MainApplication.class.getResource(championsFolder + getChampionFileName(c.name + "_Original"));
        }
        return new Image(String.valueOf(u));
    }


    public static void preCalc() {
        desaturate = new ColorAdjust();
        desaturate.setSaturation(-0.9);
        desaturate.setBrightness(-0.5);
    }
}
