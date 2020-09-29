package simulator;
import creature.*;
import die.*;
import java.util.Random;

public class Simulator{
    public static boolean debug = false;

    static final int COMBAT_RESOLUTION_DIE_SIZE=20;
    static final int CRITICAL_HIT=COMBAT_RESOLUTION_DIE_SIZE;
    static final int CRITICAL_MISS=1;

    public static int attack(Creature attacker, Creature target, Random rand){
        //Consider: Split diceExpr on regexp that covers d, +, and -
        String diceExpr=attacker.getWeapon().getDice();
        int dice=Integer.parseInt(diceExpr.substring(0, diceExpr.indexOf("d")));        
        int sides=Integer.parseInt(diceExpr.substring(diceExpr.indexOf("d")+1));
        int dmgBonus=calcAtkMod(attacker);
        int atkMod=attacker.profMod() + dmgBonus;
        int dmg=dmgBonus;
        //This code is to demonstrate reuse of a previously-developed object.
        //If this were a real game, I think treating dice as objects would be a bad idea, Tabletop Simulator aside.
        Die atkDie=new Die(COMBAT_RESOLUTION_DIE_SIZE), dmgDie=new Die(sides);        

        System.out.println(attacker.getName() + " attacks " + target.getName() + " with its " + attacker.getWeapon().getName() + "!");

        atkDie.roll(rand);

        if(debug){
            System.out.println("Natural roll: "+atkDie.getValue());
            System.out.println("A: STR +" + attacker.strMod() + ", DEX +" + attacker.dexMod() + ", PROF +" + attacker.profMod() + ", ENH +" + attacker.getWeapon().getEnhancement() + ", DMG " + attacker.getWeapon().getDice());
            System.out.println("D: ARM +" + target.getArmor().getBonus() + ", DEX +" + Math.min(target.dexMod(), target.getArmor().getMaxDex()));
        }

        if (atkDie.getValue()==CRITICAL_HIT){
            dmg+=rollDamageDice(dmgDie, dice<<1, rand);
            System.out.println("Critical hit! " + dmg + " damage!");
            return dmg;
        }

        if (atkDie.getValue()==1){
            System.out.println("Critical miss!");
            return 0;
        }

        if (atkDie.getValue()+atkMod < target.getTouchAC()){
            System.out.println("It misses!");
            return 0;
        }

        if (atkDie.getValue()+atkMod < target.getAC()){
            System.out.println("The attack is turned aside by " + target.getName() + "'s " + target.getArmor().getName() + "!");
            return 0;
        }

        dmg+=rollDamageDice(dmgDie, dice, rand);
        System.out.println("The attack hits for " + dmg + " damage!");
        return dmg;
    }

    private static int calcAtkMod(Creature c){
      //This could be a method of Creature instead but it doesn't seem to matter where it is for now.
      int abilityMod = c.getWeapon().isFinesse() ? Math.max(c.dexMod(), c.strMod()) : c.strMod();
      return abilityMod + c.getWeapon().getEnhancement();
    }

    private static int rollDamageDice(Die dmgDie, int times, Random rand){
      int acc=0;
      for (int i=0;i<times;i++){
        dmgDie.roll(rand);
        acc+=dmgDie.getValue();
      }
      return acc;
    }
}