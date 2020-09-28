import creature.*;
import armor.*;
import weapon.*;
import java.util.Random;

public class Simulator{
    public static void main(final String[] args) {
        long seed=new Random().nextLong();
        Random rand=new Random(seed);
        int dmg;

        Creature newbie = new Creature("Newbie", 1, 16, 14);
        newbie.equip(new Armor("scale mail", 4, 2));
        newbie.equip(new Weapon("longsword", "1d10", false));
        Creature goblin = new Creature("goblin", 2, 8, 14);
        goblin.equip(new Armor("leather armor", 1, Integer.MAX_VALUE));
        goblin.equip(new Weapon("shortsword", "1d6", true));
        
        System.out.println("Debug: Seed " + seed);
        dmg = attack(newbie, goblin, rand);
        //code handling change in HP would go here

        dmg = attack(goblin, newbie, rand);
        //code handling change in HP would go here
    }

    static int attack(Creature attacker, Creature target, Random rand){
        String diceExpr=attacker.getWeapon().getDice();
        int dice=Integer.parseInt(diceExpr.substring(0, diceExpr.indexOf("d")));        
        int sides=Integer.parseInt(diceExpr.substring(diceExpr.indexOf("d")+1));
        int ac=10 + target.getArmor().getBonus() + Math.min(target.dexMod(), target.getArmor().getMaxDex());
        int dmgBonus=attacker.getWeapon().getEnhancement() + ( attacker.getWeapon().isFinesse() ? attacker.dexMod() : attacker.strMod() );
        int atkMod=attacker.profMod() + dmgBonus;
        int dmg=dmgBonus;

        System.out.println(attacker.getName() + " attacks " + target.getName() + " with its " + attacker.getWeapon().getName() + "!");
        int roll=rand.nextInt(20)+1;
        if (roll==20){
            //using 5E rules where dice are doubled on a crit but static modifiers are not
            for (int i=0;i<dice*2;i++){
                dmg+=rand.nextInt(sides)+1;
            }
            System.out.println("Critical hit! " + dmg + "damage!");
            return dmg;
        }
        if (roll+atkMod < 10 && roll+atkMod < ac){
            System.out.println("It misses!");
            return 0;
        }
        if (roll+atkMod < ac){
            System.out.println("The attack is turned aside by " + target.getName() + "'s " + target.getArmor().getName() + "!");
            return 0;
        }
        for (int i=0;i<dice;i++){
            dmg+=rand.nextInt(sides)+1;
        }
        System.out.println("The attack hits for " + dmg + " damage!");
        return dmg;


    }

    
}