import simulationManager.simulation.*;


public class Main {


    public static void main(String[] args) {


    }
}

/*
TO IMPLEMENT:
    NON STACKED TEAR LEGENDARIES (+ LIFELINE ON ARCHANGELS)

    ability is CC boolean -> (shojin, evenshroud, (zekes?), aftershock ...
    buffing items? (zekes, staff..., mandate??)
    add roa stages?
    add items to enemy (like frozen heart)
    potions
    are mythic components exclusive with mythics?

    add MS (only for hecarim?)
    calculate resistances better (like FoN bonus mr)


UNTESTED:
    conqueror, grasp, sheen items
    items while not time running?
    alacrity, eyeball, gathering... stacks
    radiant virtue max hp on cast
    sudden impact
    galeforce and other executes early, should be late
    prowlers active

    DUMMY STATS

IMPERFECTIONS:
    not taken into account several instance of damage from one ability (affects black cleaver and ludens)
    sudden impact doesn't chack for a dash (everything will count as a dash)
    all healing and movement speed is ignored (irrelevant for calculations)
    some item passives (like hextech alternator) may not stack (having 2 hextech alternator will not apply the passive 2 times)

    shadowflame before or after %magicPen? (simulation is optimistic aka after)


strange variables:
    instaStackBlackCleaver
    autoBetweenAbilities


MISSING ITEMS:
    //archangels -> seraphs
    //ardent
    //putrifier
    //dead mans
    //force of nature
    //gargoyle
    //knights vow
    //manamune -> muramana
    //mikaels
    //redemption
    //spirit visage
    //staff
    //thornmail
    //chemtank
    //winter's -> fimbulwinter
    //zekes

    //stopwatch

 */
