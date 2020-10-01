package util;
import java.util.Random;

public class Rand{
    private static Random rand=new Random();

    public static void setSeed(long seed){
        rand.setSeed(seed);
    }

    public static int nextInt(int bound){
        return rand.nextInt(bound);
    }
}
