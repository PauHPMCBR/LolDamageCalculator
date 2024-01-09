package com.damagecalculator.simulationManager.simulation.items;

import com.damagecalculator.simulationManager.simulation.DamageType;
import com.damagecalculator.simulationManager.simulation.Item;
import com.damagecalculator.simulationManager.simulation.ItemType;

public class IcebornGauntlet extends Item {
    public static final String name = "Iceborn Gauntlet";
    public static final ItemType type = ItemType.LEGENDARY;
    public static final int cost = 2600;

    public IcebornGauntlet() {
        super(name, type, cost);
        ah = 15;
        hp = 300;
        armor = 50;
        item_cooldown = 1.5f;
    }

    public void onHit() { //ignoring damage reduction part!
        if (canUse()) {
            if (owner.can_use_sheen) {
                owner.can_use_sheen = false;
                putOnCooldown();
                damageDealt += cs.damage.applyDamage(DamageType.physicalDmg, owner.BASE_AD, 1);
            }
        }
    }

    @Override
    public Item makeCopy() {
        return new IcebornGauntlet();
    }
}
