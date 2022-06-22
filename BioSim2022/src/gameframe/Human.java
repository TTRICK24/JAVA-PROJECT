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
        setHealth(80);
        phase=0;
        
    }
    
    
    int countdown=0;
    int count=0;
    public void act() {
        if (phase==2){
            if(countdown>0){
                countdown=countdown-1;
        
            }
        }
        if (phase==0){
            count+=1;
            if (count>50){
                randomizeMotion();
                borderCollision();
            }
        }
        
        runAway();
        
        onScreen();
        
        
        move();
        checkFoodCollision();
        handleHealth();
        
        
        
       
    }
    
    public void randomizeMotion() {
        int r=Randomizer.getInteger(1,200);
        if (r<=1) {
            double newDirection=Randomizer.getDouble(0,360);
            
            setDirection(newDirection);
            slow();
        }
        
        
    }
   
    public void runAway(){
        ArrayList<Actor> ac = getWorld().getActors();
        
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
                        if(d<300){
                            closeThreat=temp;
                        }

                    }  
                }
            }
        }
            if (closeThreat==null) {
                phase=0;//do nothing
                count=0;
            }
            if (closeThreat!=null) {
                System.out.println("zombie nearby" + closeThreat.getX() + "," + closeThreat.getY() );
                System.out.println("" + getX() + " " + getY() );
                
                if((countdown==0||phase==0)||(countdown==0&& phase==0)){
                    setDirection(directionTo(closeThreat)-180);
                    phase=1;
                }
                double zd=directionTo(closeThreat);
                System.out.println(zd);
                slow();
                if(zd==getDirection()||zd-getDirection()>-20 && zd-getDirection()<20){
                    
                    turn(180);
                }
                    if(getX()<=30 && getY()<=30){
                        System.out.println("IN CORNER");
                        phase=2;  
                        if(zd<=360 && zd>=315){
                            
                            setDirection(270);
                            countdown=80;
                            System.out.println("HIHIHIHIHIHIHIHIHI");
                        }
                           
                        else if(zd<315 && zd>=270){
                            setDirection(0);
                            countdown=80;
                            System.out.println("HWIOAHTIOAWHTOHWA");
                        }
                    }
                    else if(getX()<=30 && getY()>=580){
                        if(zd<=90 && zd>=45){
                            setDirection(360);
                            countdown=80;
                               
                        }
                        else if(zd<45 && zd>=0){
                            setDirection(90);
                            countdown=80;
                        }
                    }
                    else if(getX()>=780 && getY()>=580){
                        if(zd>=90 && zd<=135){
                            setDirection(180);
                            countdown=80;
                                        
                        }
                        else if(zd>135 && zd<=180){
                            setDirection(90);
                            countdown=80;
                        }
                    }
                    else if(getX()>=780 && getY()<=30){
                        if(zd>=180 && zd<225){
                            setDirection(270);
                            countdown=80;
                        }
                        else if(zd>=225 && zd<=270){
                            setDirection(180);
                            countdown=80;
                            }
                        
                    }
                    else if(getX()==30 && getY()!=30 && getY()!=580){
                        System.out.println("on left border");
                        if(getDirection()>=90 && getDirection()<=180){
                            setDirection(90);
                            countdown=80;
                        }
                        else if(getDirection()>=180 && getDirection()<=270){
                            setDirection(270);
                            countdown=80;
                        }
                       
                    }
                    else if(getX()>=780 && getY()!=30 && getY()!=580){
                        System.out.println("on right border");
                         if(getDirection()>=0 && getDirection()<=90){
                            setDirection(90);
                            countdown=80;
                        }
                        else if(getDirection()>=270 && getDirection()<=360){
                            setDirection(270);
                            countdown=80;
                        }
                    }
                    else if(getY()==30 && getX()!=780 && getX()!=30){
                        System.out.println("on top border");
                        if(getDirection()>=90 && getDirection()<=180){
                            setDirection(180);
                            countdown=80;
                        }
                        else if(getDirection()<=90 && getDirection()>=0){
                            setDirection(0);
                            countdown=80;
                        }
                    }
                    else if(getY()==580 && getX()!=780 && getX()!=30){
                        System.out.println("on bottom border");
                        if(getDirection()>=180 && getDirection()<=270){
                            setDirection(180);
                            countdown=80;
                        }
                        else if(getDirection()>=270 && getDirection()<=360){
                            setDirection(0);
                            countdown=80;
                        }
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
        setDirection(270);
    }
    
    public void handleHealth() {
        if (getHealth()<=0) {
            System.out.println("handling health");    
            deactivate();
            Death death = new Death();
            getWorld().add( death, getX(), getY() );
           
        }
        else if (getHealth()<=50){
            setColor(Color.RED);
            
        }
        else if (getHealth()<=100){
            setColor(Color.ORANGE);
           
        }
       
    }
   
    public void slow(){
        if (getColor().equals(Color.RED)){
            double newSpeed=Randomizer.getDouble(0.4, 0.7);
            System.out.println("hungry! slowing down");
            setSpeed(newSpeed);
        }
        else if (getColor().equals(Color.ORANGE)){
            double newSpeed=Randomizer.getDouble(1.2, 1.5);
            setSpeed(newSpeed);
        }
        else{
            double newSpeed=Randomizer.getDouble(2.0, 2.3);
            setSpeed(newSpeed);
        }
    }
    
   public void starve(){
      int count=0;
        for(int k=0; k<getActors().size(); k++) {
             Actor temp = getActors().get(k);
             if ( temp instanceof Food && temp.isActive() ) {
                if (distanceTo(temp)<getSize()){
                    count=count+1;
                }
                if (distanceTo(temp)>getSize()){
                    count=count-1;
                }
                if (count<0){
                    setHealth(getHealth()-50);
                
             }
            }
        }
   }
      public void borderCollision(){
        int turnNum=0;
        if (getDirection()>270 && getDirection()<360){
            turnNum=2;
        }
        else if (getDirection()>180 && getDirection()<=270){
            turnNum=1;
        }
        else if (getDirection()>90 && getDirection()<=180){
            turnNum=3;
        }
        else if (getDirection()>=0 && getDirection()<=90){
            turnNum=2;
        }
        if(getY()>580){
            
            if (turnNum==1||turnNum==3){
                turn(90);
            }
             if (turnNum==2){
                turn(270);
            }
            
        }    
        if(getX()>780){
            
            if (turnNum==2){
                turn(270);
            }
            if (turnNum==2){
                turn(90);
            }
            
        }
        if(getY()<30){
            
            if (turnNum==1){
                turn(270);
            }
            if (turnNum==2){
                turn(90);
            }
           
        }
        if(getX()<30){
            
            if (turnNum==1){
                turn(90);
            }
            if (turnNum==2||turnNum==3){
                turn(270);
            }
            
        }
        
    }

}