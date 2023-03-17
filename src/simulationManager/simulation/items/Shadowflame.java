package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Shadowflame extends Item {
    public static final String name = "Shadowflame";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    float specialMagicPen;

    public Shadowflame() {
        super(name, type, cost);
        ap = 100;
        hp = 200;

        specialMagicPen = 10;
    }

    public void specialStats() {
        owner.MAGIC_PEN += specialMagicPen;
    }

    public void extraDmg() {
        owner.MAGIC_PEN -= specialMagicPen;
        float normalizedHP = Math.max(1000, Math.min(2500, owner.getEnemy().HP));
        specialMagicPen = 10 + (2500 - normalizedHP)/150;
        owner.MAGIC_PEN += specialMagicPen; //should be like magic pen in theory, ignoring shield part
    }

    public void onHit() {
        extraDmg();
    }

    @Override
    public Item makeCopy() {
        return new Shadowflame();
    }
}
