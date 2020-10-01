package simulator;
import actors.*;
import util.*;

public class Simulator{
    public static boolean debug = false;

    private static final int COMBAT_RESOLUTION_DIE_SIZE=20;
    private static final int CRITICAL_HIT=COMBAT_RESOLUTION_DIE_SIZE;
    private static final int CRITICAL_MISS=1;

    public static int attack(Creature attacker, Creature target){
        //Consider: Split diceExpr on regexp that covers d, +, and -
        int[] parsedDice = parseDice(attacker.getWeapon().getDice());
        int dice=parsedDice[0];
        int sides=parsedDice[1];
        int dmgBonus=calcAtkMod(attacker);
        int atkMod=attacker.profMod() + dmgBonus;
        int dmg=dmgBonus;
        //This code is to demonstrate reuse of a previously-developed object.
        //If this were a real game, I think treating dice as objects would be a bad idea, Tabletop Simulator aside.
        Die atkDie=new Die(COMBAT_RESOLUTION_DIE_SIZE), dmgDie=new Die(sides);        

        atkDie.roll();

        if(debug){
            System.out.println("Natural roll: "+atkDie.getValue());
            System.out.println("A: STR +" + attacker.strMod() + ", DEX +" + attacker.dexMod() + ", PROF +" + attacker.profMod() + ", ENH +" + attacker.getWeapon().getEnhancement() + ", DMG " + attacker.getWeapon().getDice());
            System.out.println("D: ARM +" + target.getArmor().getBonus() + ", DEX +" + Math.min(target.dexMod(), target.getArmor().getMaxDex()));
        }

        if (atkDie.getValue()==CRITICAL_HIT){
            dmg+=rollDamageDice(dmgDie, dice<<1);
            System.out.println("Critical hit! " + dmg + " damage!");
            return dmg;
        }

        if (atkDie.getValue()==CRITICAL_MISS){
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

        dmg+=rollDamageDice(dmgDie, dice);
        System.out.println("The attack hits for " + dmg + " damage!");
        return dmg;
    }

    public static int[] parseDice(String diceExpr){
      //Consider: Split diceExpr on regexp that covers d, +, and -
      int dice=Integer.parseInt(diceExpr.substring(0, diceExpr.indexOf("d")));        
      int sides=Integer.parseInt(diceExpr.substring(diceExpr.indexOf("d")+1));
      int[] a = {dice, sides};
      return a;
    }

    public static int calcAtkMod(Creature c){
      //This could be a method of Creature instead but it doesn't seem to matter where it is for now.
      int abilityMod = c.getWeapon().isFinesse() ? Math.max(c.dexMod(), c.strMod()) : c.strMod();
      return abilityMod + c.getWeapon().getEnhancement();
    }

    public static int rollDamageDice(Die dmgDie, int times){
      int acc=0;
      for (int i=0;i<times;i++){
        dmgDie.roll();
        acc+=dmgDie.getValue();
      }
      return acc;
    }
}