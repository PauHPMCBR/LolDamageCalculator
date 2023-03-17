package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RodOfAges extends Item {
    public static final String name = "Rod of Ages";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 2800;

    int stacks;

    public RodOfAges(int stacks) {
        super(name, type, cost);
        ap = 60;
        hp = 400;
        mana = 400;

        this.stacks = Math.min(stacks, 10);
    }

    public RodOfAges() {
        super(name, type, cost);
        ap = 60;
        hp = 400;
        mana = 400;

        stacks = 10;
    }

    public void specialStats() {
        int bonusHP = 20 * stacks;
        int bonusMana = 20 * stacks;
        int bonusAP = 4 * stacks;
        if (stacks == 10) {
            bonusAP *= 1.5f;
            bonusMana *= 1.5f;
            bonusHP *= 1.5f;
        }
        owner.BONUS_HP += bonusHP;
        owner.AP += bonusAP;
        owner.MANA += bonusMana;
    }

    public void applyMythicPassive() {
        owner.AH += 5 * owner.legendary_items_carried;
    }


    @Override
    public Item makeCopy() {
        return new RodOfAges(stacks);
    }
}
