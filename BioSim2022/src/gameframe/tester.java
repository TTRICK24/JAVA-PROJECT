
package gameframe;

public class tester {
    
    public static void main(String[] args) {
            World w = new World();
            Human h1 = new Human();
            Human h2 = new Human();
            Human h3 = new Human();
            w.add(h1, 100,100);
            w.add(h2, 200,200);
            double distance = h1.distanceTo(h2);
            System.out.println(distance);
    }
}
