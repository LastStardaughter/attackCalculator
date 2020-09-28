package die;
import java.util.Random;

public class Die{
    private short sides;
    private int value;

    //For this application if we're not specifying the number of sides something is probably wrong!
    /*
    public Die(){
        this.sides=6;
    }
    */

    public Die(int sides){
        this.sides=(short) sides;
    }

    public void roll(Random rand){
        value = rand.nextInt(sides)+1;
    }

    public int getValue(){
        return value;
    }

    public int getSides(){
        return (int) sides;
    }
}