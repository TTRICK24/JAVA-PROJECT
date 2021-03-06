/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Zombie extends Actor {
    
    Actor myPrey=null;
    int phase=0;
    
   
    public Zombie() {
        super(); 
        setColor(Color.GREEN);
        setSize(25);
        setHealth(80);
        phase=0;
    }


 public void act() {
        onScreen();
        hunt();
        move(); 
        
       
        if(phase==0){
            setSpeed(0.7);
            randomizeMove();
            hunt();
            move();
        }
        
    }
public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillRect( getX()-getRoundedSize()/2, getY()-getRoundedSize()/2, getRoundedSize(), getRoundedSize() );
        g.setColor( Color.GREEN);
        g.fillRect( getX()-getRoundedSize()/4, getY()-getRoundedSize()/4, getRoundedSize()/2, getRoundedSize()/2 );
    }

public void randomizeMove(){
     int r=Randomizer.getInteger(1,100);
        if (r<=1) {
            
            double newDirection=Randomizer.getDouble(0,360);
            
            setDirection(newDirection);
        }
       
        
}
public void hunt(){
   
    ArrayList<Actor> ac = getWorld().getActors();
    double dt=0;
    Actor prey=null;
    //make sure to check later, that prey is not null before trying to use it!   
    if(ac.size()>0){
        double closest=9999999;
        for(int k=0; k<ac.size();k++){
            Actor temp = ac.get(k);
            if (temp.isActive() && temp instanceof Human) {
               double d = distanceTo(temp);
               if(d<closest){
                   closest=d;
                   prey=temp;
               }  
            }
        }
        if (prey==null) {
            phase=0;//do nothing
            
        }
        if (prey!=null) {
            phase=1;
            dt=directionTo(prey);  
            double newSpeed=Randomizer.getDouble(0.7, 1.2);
            setSpeed(newSpeed);
            setDirection(dt);
            if(distanceTo(prey)<getSize() )
                attack((Human)prey);

        }
    
   
    }
   
}
public void attack(Human prey){
        prey.takeDamage(2);
        System.out.println("attacking " + prey);
        
        
     
    
}        
      

} //eoc