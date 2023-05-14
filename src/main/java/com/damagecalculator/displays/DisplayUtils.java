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
    public static Image getItemImage(Item i) {
        if (i == null) return getEmptyItemImage();

        URL u = MainApplication.class.getResource(itemsFolder + getItemFileName(i));
        return new Image(String.valueOf(u));
    }
    public static Image getEmptyItemImage() {
        return new Image(String.valueOf(MainApplication.class.getResource(itemsFolder + "Empty_item.png")));
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
