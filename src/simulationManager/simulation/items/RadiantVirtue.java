package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RadiantVirtue extends Item {
    public static final String name = "Radiant Virtue";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3200;

    float bonusHPGained;

    public RadiantVirtue() {
        super(name, type, cost);
        ah = 20;
        hp = 400;
        armor = 30;
        mr = 30;
        item_cooldown = 90;

        bonusHPGained = 0;
    }

    public void extraDmg() { //supposing insta ult, ignoring healing part
        if (canUse()) {
            lastUsed = cs.time - item_cooldown*(1 - 100/(100+owner.ITEM_HASTE + owner.AH));
            bonusHPGained = 0.15f * owner.getMaxHP();
            owner.BONUS_HP += bonusHPGained;
        }
        if (lastUsed < cs.time + 9 && bonusHPGained != 0) { //9s duration, then remove extra hp
            owner.BONUS_HP -= bonusHPGained;
            bonusHPGained = 0;
        }
    }

    public void applyMythicPassive() {
        owner.BONUS_HP += 100 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new RadiantVirtue();
    }
}
