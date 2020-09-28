package armor;

public class Armor {
    String name;
    int armorBonus, maxDex, enhancement;

    public Armor(String name, int armor, int maxDex)
    {
        this.name=name;
        this.armorBonus=armor;
    }

    public Armor(String name, int armor, int maxDex, int enhancement)
    {
        this.name=name;
        this.armorBonus=armor;
        this.enhancement=enhancement;
    }

    public String getName(){
        return name;
    }

    public int getMaxDex(){
        return maxDex;
    }

    public int getBonus(){
        return armorBonus + enhancement;
    }
}
