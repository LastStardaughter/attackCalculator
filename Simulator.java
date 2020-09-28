import creature.*;
import armor.*;
import weapon.*;
import die.*;
import java.util.Random;

public class Simulator{

    static boolean debug=false;

    public static void main(final String[] args) {
        Random rand;
        if (debug){
            long seed=new Random().nextLong();
            rand=new Random(seed);
            System.out.println("Debug: Seed " + seed);
        }   else{rand = new Random();}

        int dmg;

        Creature newbie = new Creature("Newbie", 1, 16, 14);
        newbie.equip(new Armor("scale mail", 4, 2));
        newbie.equip(new Weapon("longsword", "1d10", false));
        Creature goblin = new Creature("goblin", 2, 8, 14);
        goblin.equip(new Armor("leather armor", 1));
        goblin.equip(new Weapon("shortsword", "1d6", true));
        
        dmg = attack(newbie, goblin, rand);
        //code handling change in HP would go here

        dmg = attack(goblin, newbie, rand);
        //code handling change in HP would go here
    }

    static int attack(Creature attacker, Creature target, Random rand){
        //There's probably a very elegant way to parse dice expressions with regexps, might look into that in the future.
        String diceExpr=attacker.getWeapon().getDice();
        int dice=Integer.parseInt(diceExpr.substring(0, diceExpr.indexOf("d")));        
        int sides=Integer.parseInt(diceExpr.substring(diceExpr.indexOf("d")+1));
        int ac=10 + target.getArmor().getBonus() + Math.min(target.dexMod(), target.getArmor().getMaxDex());
        int dmgBonus=attacker.getWeapon().getEnhancement() + ( attacker.getWeapon().isFinesse() ? attacker.dexMod() : attacker.strMod() );
        int atkMod=attacker.profMod() + dmgBonus;
        int dmg=dmgBonus;
        //We're instantiating this class only to throw it away at the end of the method... passing it from Main() adds nothing though...
        //I think treating dice as objects is a bad idea if this were an actual computer game, Tabletop Simulator aside.
        Die atkDie=new Die(20), dmgDie=new Die(sides);        

        System.out.println(attacker.getName() + " attacks " + target.getName() + " with its " + attacker.getWeapon().getName() + "!");

        //int roll=rand.nextInt(20)+1;
        atkDie.roll(rand);

        if(debug){
            System.out.println("Natural roll: "+atkDie.getValue());
            System.out.println("A: STR +" + attacker.strMod() + ", DEX +" + attacker.dexMod() + ", PROF +" + attacker.profMod() + ", ENH +" + attacker.getWeapon().getEnhancement() + ", DMG " + attacker.getWeapon().getDice());
            System.out.println("D: ARM +" + target.getArmor().getBonus() + ", DEX +" + Math.min(target.dexMod(), target.getArmor().getMaxDex()));
        }

        //If the roll was a natural 20, we auto-hit and crit, so no comparison to AC is necessary.
      //if (roll==20){
        if (atkDie.getValue()==20){
            //using 5E rules where there is no confirmation roll and dice are doubled on a crit but static modifiers are not
            for (int i=0;i<dice*2;i++){
              //dmg+=rand.nextInt(sides)+1;
                dmgDie.roll(rand);
                dmg+=dmgDie.getValue();
            }
            System.out.println("Critical hit! " + dmg + " damage!");
            return dmg;
        }
        //For descriptive fluff, I've decided to describe a failed result <10 as a "miss" while a failed result >=10 "fails to bypass armor"
        //This way we also get some use out of the armor's name field.
      //if (roll+atkMod < 10 && roll+atkMod < ac){
        if (atkDie.getValue()+atkMod < 10 && atkDie.getValue()+atkMod < ac){
            System.out.println("It misses!");
            return 0;
        }
      //if (roll+atkMod < ac){
        if (atkDie.getValue()+atkMod < ac){
            System.out.println("The attack is turned aside by " + target.getName() + "'s " + target.getArmor().getName() + "!");
            return 0;
        }
        for (int i=0;i<dice;i++){
          //dmg+=rand.nextInt(sides)+1;
            dmgDie.roll(rand);
            dmg+=dmgDie.getValue();
        }
        System.out.println("The attack hits for " + dmg + " damage!");
        return dmg;
    }
}