/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


/**
 *
 * @author 992041
 */
public class Zombie extends Actor {
    public Zombie() {
        super(); 
        setColor(Color.GREEN);
        setSize(25);
    }


 public void act() {
        
        move(); 
        
    }
public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillRect( getX()-getRoundedSize()/2, getY()-getRoundedSize()/2, getRoundedSize(), getRoundedSize() );
        g.setColor( Color.GREEN);
        g.fillRect( getX()-getRoundedSize()/4, getY()-getRoundedSize()/4, getRoundedSize()/2, getRoundedSize()/2 );
    }

public void hunt(){
   
    ArrayList<Actor> ac = getWorld().getActors();
    double dt=0;
    if(ac.size()>0){
    double closest= distanceTo(ac.get(0));
    for(int k=0; k<ac.size();k++){
        Actor temp = ac.get(k);
        if (temp.isActive() && temp instanceof Human) {
            double d = distanceTo(temp);
           if(d<closest){
               closest=d;
           }
     dt=directionTo(temp);   
        }
        setDirection(dt);
        move();
    }
}
}

}