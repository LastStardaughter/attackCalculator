package creature;
import weapon.*;
import armor.*;

public class Creature {
    String name;
    int str, dex, lvl;
    Weapon weapon;
    Armor armor;

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

    public String getName(){
        return name;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Armor getArmor(){
        return armor;
    }
    
}
