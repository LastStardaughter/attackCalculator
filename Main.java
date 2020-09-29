import creature.*;
import armor.*;
import weapon.*;
import harmless.*;
import simulator.*;
import java.util.Random;

public class Main {
    static boolean debug=false;

    public static void main(final String[] args) {
        Random rand;
        if (debug){
            Simulator.debug=true;
            long seed=new Random().nextLong();
            rand=new Random(seed);
            System.out.println("Debug: Seed " + seed);
        }   else{rand = new Random();}

        Creature newbie = new Creature("Newbie", 1, 16, 14);
        newbie.equip(new Armor("scale mail", 4, 2));
        newbie.equip(new Weapon("longsword", "1d10", false));
        Creature goblin = new Creature("goblin", 2, 8, 14);
        goblin.equip(new Armor("leather armor", 1));
        goblin.equip(new Weapon("shortsword", "1d6", true));
        Creature veteran = new Creature("Veteran adventurer", 10, 18, 12);
        veteran.equip(new Armor("+1 full plate", 8, 0, 1));
        veteran.equip(new Weapon("+1 greatsword", "2d6", false, 1));
        Creature dragon = new Creature("Young red dragon", 10, 23, 10);
        dragon.equip(new Armor("scaly hide", 8));
        dragon.equip(new Weapon("bite", "2d10", true));
        Harmless slime = new Harmless("ghost slime", "jiggles");
        
        newbie.attack(goblin, rand);
        goblin.attack(newbie, rand);
        slime.attack(newbie, rand);
        dragon.attack(veteran, rand);
        veteran.attack(dragon, rand);
    }
}
