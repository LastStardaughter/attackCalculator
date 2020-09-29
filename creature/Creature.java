package creature;
import weapon.*;
import armor.*;
import simulator.*;
import combatant.*;
import java.util.Random;

public class Creature implements Combatant{
    private String name;
    private int str, dex, lvl;
    private Weapon weapon;
    private Armor armor;

    public Creature(String name, int lvl, int str, int dex){
        this.name=name;
        this.str=str;
        this.dex=dex;
        this.lvl=lvl;
    }

    public Creature(String name, int lvl, int str, int dex, Weapon weapon, Armor armor){
        this.name=name;
        this.str=str;
        this.dex=dex;
        this.lvl=lvl;
        this.weapon=weapon;
        this.armor=armor;
    }

    public void equip(Weapon weapon){
        this.weapon=weapon;
    }

    public void equip(Armor armor){
        this.armor=armor;
    }

    public int strMod(){
        return (str-10)/2;
    }    

    public int dexMod(){
        return (dex-10)/2;
    }

    public int profMod(){
        return 2+(lvl-1)/4;
    }

    public int getAC(){        
        return BASE_ARMOR_CLASS + armor.getBonus() + Math.min(dexMod(), armor.getMaxDex());
    }

    public int getTouchAC(){
        return BASE_ARMOR_CLASS + Math.min(dexMod(), armor.getMaxDex());
    }

    public String getName(){
        return name;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Armor getArmor(){
        return armor;
    }
    
    public void attack(Combatant target, Random rand){
        System.out.println(name + " attacks " + target.getName() + " with its " + weapon.getName() + "!");
        
        if (target instanceof Creature){
            int dmg = Simulator.attack(this, (Creature) target, rand);
            //would probably need refactoring to apply damage but that's not part of this demonstration.
            //target.takeDmg(dmg);
        } else {
            target.respond(this);
        }
    }

    public void respond(Combatant attacker){
        System.out.println("ERROR: Unhandled combat: " + attacker.getClass().toString() + " vs Creature.");
    }
}
