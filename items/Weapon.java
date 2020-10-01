package items;

public class Weapon {
    private String name, dice;
    private boolean finesse=false;
    private int enhancement;

    public Weapon(String name, String dice){
        this.name=name;
        this.dice=dice;
    }

    public Weapon(String name, String dice, boolean finesse){
        this.name=name;
        this.dice=dice;
        this.finesse=finesse;
    }

    public Weapon(String name, String dice, int enhancement){
        this.name=name;
        this.dice=dice;
        this.enhancement=enhancement;
    }

    public Weapon(String name, String dice, boolean finesse, int enhancement){
        this.name=name;
        this.dice=dice;
        this.finesse=finesse;
        this.enhancement=enhancement;
    }

    public String getName(){
        return name;
    }

    public String getDice(){
        return dice;
    }

    public boolean isFinesse(){
        return finesse;
    }

    public int getEnhancement(){
        return enhancement;
    }
    
}
