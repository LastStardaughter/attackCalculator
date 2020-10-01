package actors;

public class Harmless implements Combatant {
    private String name, verb;

    public Harmless(String name, String verb){
        this.name=name;
        this.verb=verb;
    }

    public String getName(){
        return name;
    }

    public void attack(Combatant target){
        System.out.println("The " + name + " " + verb + " at " + target.getName() + " menacingly but harmlessly!");
        return;
    }

    public void respond(Combatant attacker){
        System.out.println("The " + name + " dies!");
    }
}
