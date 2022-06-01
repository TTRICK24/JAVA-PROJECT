package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Death extends Actor {
        
    public Death() {
        super(); 
        setColor(Color.BLACK);
        setSize(10);
    }
    
    public void act() {
        //food does nothing! just sits there       
    }
    
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.drawLine(getX()-10, getY()-10, getX()+10, getY()+10);
        g.drawLine(getX()+10, getY()-10, getX()-10, getY()+10);
    }
    
}
