package harmless;
import combatant.*;
import creature.*;
import java.util.Random;

public class Harmless implements Combatant {
    private String name, verb;

    public Harmless(String name, String verb){
        this.name=name;
        this.verb=verb;
    }

    public String getName(){
        return name;
    }

    public void attack(Creature target, Random rand){
        System.out.println("The " + name + " " + verb + " at " + target.getName() + " menacingly but harmlessly!");
        return;
    }
}
