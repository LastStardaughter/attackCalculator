package combatant;
import creature.*;
import java.util.Random;

public interface Combatant {
    public static final int BASE_ARMOR_CLASS = 10; //Not sure which access modifier is appropriate for this situation
    public void attack(Creature target, Random rand);
    //public void takeDmg(int dmg);
    //Example of another method that would be appropriate for the interface
    //Creatures could have hit points and actually track damage, Harmless might be instantly slain instead.
    //Some sort of puzzle monster that can't be killed but must be dealt with another way might be able to take infinite damage.
    //A 'die' or 'kill' method might also be appropriate,
    //on the other hand that seems less like part of the interface and more like a possible but not guarunteed result of taking damage.
}
