package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Human extends Actor {
    
    private Actor closeThreat=null;
    private Actor closeThreat1=null;
    int phase;
    public Human() {
        super(); 
        setColor(Color.BLUE);
        setSize(25);
        setHealth(20);
        phase=0;
    }
    
    int count=0;
    public void act() {
        
        count=+1;
        if (phase==0){
            randomizeMotion();
        }
        
        
        runAway();
        
        
        onScreen();
        move();
        checkFoodCollision();
        handleHealth();
        
        if (count > 80){
        count=0;}
       
    }
    
    public void randomizeMotion() {
        int r=Randomizer.getInteger(1,100);
        if (r<=1) {
            double newSpeed=Randomizer.getDouble(0.5, 2.0);
            double newDirection=Randomizer.getDouble(0,360);
            setSpeed(newSpeed);
            setDirection(newDirection);
        }
        if ( distanceTo(400,300) > 500 ) {
            double toCenter = directionTo(400,300);
            setDirection(toCenter);
        }
        
    }
    public void runAway(){
        ArrayList<Actor> ac = getWorld().getActors();
        double dt=0;
        Actor closeThreat=null;
        double closest=999999;
    //make sure to check later, that prey is not null before trying to use it!   
        if(ac.size()>0){
            
            for(int k=0; k<ac.size();k++){
                Actor temp = ac.get(k);
                if (temp.isActive() && temp instanceof Zombie) {
                double d = distanceTo(temp);
                    if(d<closest){
                        closest=d;
                        if(d<200){
                            closeThreat=temp;
                        }
                        }  
                }
            }
            if (closeThreat==null) {
                phase=0;//do nothing
            }
            if (closeThreat!=null) {
                phase=1;
                dt=directionTo(closeThreat)-180;
                double newSpeed=Randomizer.getDouble(0.5, 1.0);
                setSpeed(newSpeed);
                setDirection(dt);

            }
 
   
    }
        if(ac.size()>0){
            double closest1=closest;
            for(int k=0; k<ac.size();k++){
                Actor temp = ac.get(k);
                if (temp.isActive() && temp instanceof Zombie) {
                double d = distanceTo(temp);
                    if(d<closest1){
                        closest1=d;
                        if(d<200){
                            closeThreat=temp;
                        }
                        }  
                }
            }
            if (closeThreat==null) {
                phase=0;//do nothing
            }
            if (closeThreat!=null) {
                phase=1;
                dt=directionTo(closeThreat)-180;
                double newSpeed=Randomizer.getDouble(0.5, 1.0);
                setSpeed(newSpeed);
                setDirection(dt);

            }
 
   
    }
    }
    @Override
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillRect( getX()-getRoundedSize()/2, getY()-getRoundedSize()/2, getRoundedSize(), getRoundedSize() );
        g.setColor( Color.WHITE);
        g.fillRect( getX()-getRoundedSize()/4, getY()-getRoundedSize()/4, getRoundedSize()/2, getRoundedSize()/2 );
    }
    
    public void checkFoodCollision() {
        //cycle through all actors.  see if we are touching any food.  eat and deactivate!
        for(int k=0; k<getActors().size(); k++) {
             Actor temp = getActors().get(k);
             if ( temp instanceof Food && temp.isActive() ) {
                     if (distanceTo(temp)<getSize() ) {
                         setHealth(getHealth()+10);
                         getWorld().remove(temp);  //or temp.deactivate();
                     }
             }
         }
         
    }

    public void right() {
        setDirection(0);
    }
    
    public void handleHealth() {
        if (getHealth()<=0) {
            System.out.println("handling health");    
            deactivate();
            Death death = new Death();
            getWorld().add( death, getX(), getY() );
           
        }
        else if (getHealth()<=50)
            setColor(Color.RED);
        else if (getHealth()<=100)
            setColor(Color.ORANGE);
        else
            setColor(Color.BLUE);
    }
   
   
}
