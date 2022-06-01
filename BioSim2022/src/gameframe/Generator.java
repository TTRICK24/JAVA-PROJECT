package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Generator extends Actor {
        
    public Generator() {
        super(); 
        setColor(Color.BLACK);
        setSize(10);
    }
    
    public void act() {
       int humanCount=0;
        for(Actor a: getActors() ) 
            if (a instanceof Human && a.isActive() )
                humanCount++;
        
        if (humanCount<5) 
            for(int k=0; k<5; k++)
                getWorld().add( new Human(), 300, 400);
            
        int foodCount=0;
        for(Actor a: getActors() ) 
            if (a instanceof Food && a.isActive() )
                foodCount++;
        
        if (foodCount<30) {
            for(int k=0; k<15; k++) {
                int xp = Randomizer.getInteger(1,800);
                int yp = Randomizer.getInteger(1,600);
                getWorld().add(new Food(), xp, yp );            
            }
        }  
    }
    
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillOval( getX(), getY(), getRoundedSize(), getRoundedSize() );
    }
}
