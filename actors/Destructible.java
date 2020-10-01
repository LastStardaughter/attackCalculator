package actors;
import simulator.*;
import util.Die;

public class Destructible implements Combatant{
    private String name;
    private int hardness;

    public String getName(){
        return name;
    }

    public void attack(Combatant target){
        System.out.println("WARNING: inanimate object " + name + " tried to attack " + target.getName());
    }

    public void respond(Combatant attacker){
        if (attacker instanceof Creature){
            int[] parsedDice = Simulator.parseDice(((Creature) attacker).getWeapon().getDice());
            int dmg = Math.max(0, ((Creature) attacker).strMod() + Simulator.rollDamageDice(new Die(parsedDice[1]), parsedDice[0]) - hardness);
        } else {
            System.out.println("ERROR: Unhandled combat: " + attacker.getClass().toString() + " vs Destructible.");
        }
    }
}
