package com.damagecalculator.simulationManager.simulation.runes;

import com.damagecalculator.simulationManager.simulation.Rune;
import com.damagecalculator.simulationManager.simulation.RunePath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RuneList {
    public static Rune[] precisionKeystone = new Rune[] {new PressTheAttack(), new LethalTempo(), new FleetFootwork(), new Conqueror()};
    public static Rune[] precision1 = new Rune[] {new AbsorbLife(), new Triumph(), new PresenceOfMind()};
    public static Rune[] precision2 = new Rune[] {new LegendAlacrity(10), new LegendHaste(10), new LegendBloodline()};
    public static Rune[] precision3 = new Rune[] {new CoupDeGrace(), new CutDown(), new LastStand()};

    public static Rune[] dominationKeystone = new Rune[] {new Electrocute(), new DarkHarvest(0), new HailOfBlades()};
    public static Rune[] domination1 = new Rune[] {new CheapShot(), new TasteOfBlood(), new SuddenImpact()};
    public static Rune[] domination2 = new Rune[] {new SixthSense(), new GrislyMementos(), new DeepWard()};
    public static Rune[] domination3 = new Rune[] {new TreasureHunter(), new RelentlessHunter(), new UltimateHunter(5)};

    public static Rune[] sorceryKeystone = new Rune[] {new SummonAery(), new ArcaneComet(), new PhaseRush()};
    public static Rune[] sorcery1 = new Rune[] {new AxiomArcanist(), new ManaflowBand(10), new NimbusCloak()};
    public static Rune[] sorcery2 = new Rune[] {new Transcendence(), new Celerity(), new AbsoluteFocus()};
    public static Rune[] sorcery3 = new Rune[] {new Scorch(), new Waterwalking(true), new GatheringStorm(2)};

    public static Rune[] resolveKeystone = new Rune[] {new GraspOfTheUndying(0), new Aftershock(), new Guardian()};
    public static Rune[] resolve1 = new Rune[] {new Demolish(), new FontOfLife(), new ShieldBash()};
    public static Rune[] resolve2 = new Rune[] {new Conditioning(), new SecondWind(), new BonePlating()};
    public static Rune[] resolve3 = new Rune[] {new Overgrowth(15), new Revitalize(), new Unflinching()};

    public static Rune[] inspirationKeystone = new Rune[] {new GlacialAugment(), new UnsealedSpellbook(), new FirstStrike()};
    public static Rune[] inspiration1 = new Rune[] {new HextechFlashtraption(), new MagicalFootwear(), new CashBack()};
    public static Rune[] inspiration2 = new Rune[] {new TripleTonic(), new TimeWarpTonic(), new BiscuitDelivery(3)};
    public static Rune[] inspiration3 = new Rune[] {new CosmicInsight(), new ApproachVelocity(), new JackOfAllTrades()};

    public static ArrayList<Rune> allRunes = new ArrayList<>();
    public static HashMap<String, Rune> nameToRune = new HashMap<>();
    static {
        allRunes.addAll(Arrays.asList(precisionKeystone));
        allRunes.addAll(Arrays.asList(precision1));
        allRunes.addAll(Arrays.asList(precision2));
        allRunes.addAll(Arrays.asList(precision3));

        allRunes.addAll(Arrays.asList(dominationKeystone));
        allRunes.addAll(Arrays.asList(domination1));
        allRunes.addAll(Arrays.asList(domination2));
        allRunes.addAll(Arrays.asList(domination3));

        allRunes.addAll(Arrays.asList(sorceryKeystone));
        allRunes.addAll(Arrays.asList(sorcery1));
        allRunes.addAll(Arrays.asList(sorcery2));
        allRunes.addAll(Arrays.asList(sorcery3));

        allRunes.addAll(Arrays.asList(resolveKeystone));
        allRunes.addAll(Arrays.asList(resolve1));
        allRunes.addAll(Arrays.asList(resolve2));
        allRunes.addAll(Arrays.asList(resolve3));

        allRunes.addAll(Arrays.asList(inspirationKeystone));
        allRunes.addAll(Arrays.asList(inspiration1));
        allRunes.addAll(Arrays.asList(inspiration2));
        allRunes.addAll(Arrays.asList(inspiration3));

        for (Rune r : allRunes) nameToRune.put(r.name, r);
    }

    public static Rune[] getKeystones(RunePath rp) {
        switch (rp) {
            case Precision -> {
                return precisionKeystone;
            }
            case Domination -> {
                return dominationKeystone;
            }
            case Sorcery -> {
                return sorceryKeystone;
            }
            case Resolve -> {
                return resolveKeystone;
            }
            case Inspiration -> {
                return inspirationKeystone;
            }
            default -> {
                return null;
            }
        }
    }

    public static Rune[] getColumn1(RunePath rp) {
        switch (rp) {
            case Precision -> {
                return precision1;
            }
            case Domination -> {
                return domination1;
            }
            case Sorcery -> {
                return sorcery1;
            }
            case Resolve -> {
                return resolve1;
            }
            case Inspiration -> {
                return inspiration1;
            }
            default -> {
                return null;
            }
        }
    }

    public static Rune[] getColumn2(RunePath rp) {
        switch (rp) {
            case Precision -> {
                return precision2;
            }
            case Domination -> {
                return domination2;
            }
            case Sorcery -> {
                return sorcery2;
            }
            case Resolve -> {
                return resolve2;
            }
            case Inspiration -> {
                return inspiration2;
            }
            default -> {
                return null;
            }
        }
    }

    public static Rune[] getColumn3(RunePath rp) {
        switch (rp) {
            case Precision -> {
                return precision3;
            }
            case Domination -> {
                return domination3;
            }
            case Sorcery -> {
                return sorcery3;
            }
            case Resolve -> {
                return resolve3;
            }
            case Inspiration -> {
                return inspiration3;
            }
            default -> {
                return null;
            }
        }
    }
}
