package gameframe;

public class Randomizer {
    
    //returns decimal number with 0.001 precision min-max inclusive
    public static int getInteger(int min, int max) {
        int num = (int)(Math.random()*(max-min+1))+min;
        return num;
    }
    
    public static double getDouble(double min, double max) {
        double num = getInteger((int)(min*1000), (int)(max*1000)) / 1000.0;
        return num;
    }
}
