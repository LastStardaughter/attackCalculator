package items;

public class Armor {
    private String name;
    private int armorBonus, maxDex, enhancement;

    public Armor(String name, int armor)
    {
        this.name=name;
        this.armorBonus=armor;
        this.maxDex=Integer.MAX_VALUE;
    }

    public Armor(String name, int armor, int maxDex)
    {
        this.name=name;
        this.armorBonus=armor;
        this.maxDex=maxDex;
    }

    public Armor(String name, int armor, int maxDex, int enhancement)
    {
        this.name=name;
        this.armorBonus=armor;
        this.maxDex=maxDex;
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
