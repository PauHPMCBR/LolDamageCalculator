package simulationManager.simulation.items;

import simulationManager.simulation.DamageType;
import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class LudensTempest extends Item {
    public static final String name = "Luden's Tempest";
    public static final ItemType type = ItemType.mythic;
    public static final int cost = 3200;

    public LudensTempest() {
        super(name, type, cost);
        ap = 80;
        ah = 20;
        mana = 600;
        magic_pen = 6;
        item_cooldown = 10;
    }

    public void echo() { //special funcion, will get called by class "Damage"
        if (canUse()) {
            putOnCooldown();
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, (float) (100 + 0.1 * owner.AP), 1);
        }
        lastUsed -= 0.3; //decrease cd by 0.3 secs
    }

    public void applyMythicPassive() {
        owner.MAGIC_PEN += 5 * owner.legendary_items_carried;
    }

    @Override
    public Item makeCopy() {
        return new LudensTempest();
    }
}
