package simulationManager.simulation;

public class OtherItems {

    //formula: 30 + 40/17*(champ2.lvl-1) + 0.015 maxHP + 0.075 AP
    public static float zekesOnhitDamage = 90;
    public static Item zekesOnhit = new Item("Zeke's (onHit)",0,0,0,0,0,0,0) {
        void onHit() {
            damageDealt += cs.damage.applyDamage(DamageType.magicDmg, zekesOnhitDamage); //supposing permanent uptime
        }
    };




    public static Item kaisaPassiveOnhitCall = new Item("_passive on-hit",0,0,0,0,0,0,0) {
        void onHit() {
            owner.Passive.onCall();
            owner.E.onCall();
        }
    };


    public static void initializeOthers() {
        kaisaPassiveOnhitCall.is_hidden = true;

    }
}
