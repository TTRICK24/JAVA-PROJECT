package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Actor {
    
    private World world;
    private double x, y;
    private double speed;
    private double direction;
    private long acts=0;
    private double size;
    private boolean active=true;
    private Color myColor;
    private int health=1;
    
    public Actor() {
        active=true;
        speed=0;
        direction=0;
        myColor=Color.BLACK;
        size=20;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int h) {
        health=h;
    }
    
    public void act() {
        //move every turn...
        move();
    }
    
    public void setWorld(World w) {
        world=w;
    }
    
    public World getWorld() {
        return world;
    }
    
    public ArrayList<Actor> getActors() {
        return getWorld().getActors();
    }
    
    public void move() {
       x+= speed*(Math.cos(Math.toRadians(direction)));
       y+= -1*speed*(Math.sin(Math.toRadians(direction)));  //y opposite of normal math!
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void deactivate() {
       active=false;
    }
    
    public int getX() {
        return (int)x;
    }
    
    public void setX(int x) {
        this.x=x;
    }
    
    public void setY(int y) {
        this.y=y;
    }
    
    public int getY() {
        return (int)y;
    }
    
    public int getDoubleX() {
        return (int)x;
    }
    
    public int getDoubleY() {
        return (int)y;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public double getDirection() {
        return direction;
    }
    
    public double getSize() {
        return size;
    }
    
    public int getRoundedSize() {
        return (int)size;
    }
    
    public Color getColor() {
        return myColor;
    }
    
    public void setColor(Color newColor) {
        myColor=newColor;
    }
    
    public void setSize(double newSize) {
        if (newSize>0)
            size=newSize;
    }
    
    public void setSpeed(double newSpeed) {
        speed=newSpeed;
    }
    
    public void setDirection(double newDirection) {
        direction=newDirection;
        direction=fixDirection(direction);
    }
    
    public void turn(double degrees) {
        direction+=degrees;
        direction=fixDirection(direction);
    }
    
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillOval( getDoubleX(), getDoubleY(), getRoundedSize(), getRoundedSize() );
    }    
    
    
    public double fixDirection(double d) {
       while(d<0)
            d+=360;
        while(d>360)
            d-=360;
        return d;
    }
    
    public double distanceTo(Actor A) {
       if (A==null) 
           return -1;
       return distanceTo(A.getX(), A.getY() );
    }
    
    public double distanceTo(double x, double y) {
        double dx=this.x-x;
        double dy=this.y-y;
        double distance=Math.pow((dx*dx+dy*dy), 0.5);
        return distance;        
    }
    
    public double directionTo(double x, double y) {
        double dx=x-this.x;
        double dy=-(y-this.y);
        double radian = Math.atan2(dy, dx);
        double angle = Math.toDegrees(radian);
        return fixDirection( angle );
    }
    
    public double directionTo(Actor A) {
        if (A==null) 
            return -1;
        return fixDirection( directionTo(A.getX(), A.getY() ) );
    }
    
    public void takeDamage(int dam){
        health=health-dam;
        System.out.println(""+this+ " just took damage of " + dam + " new health is " + +health);
    }
    public void onScreen(){
        int turnNum=0;
        if (direction>270 && direction<360){
            turnNum=2;
        }
        else if (direction>180 && direction<=270){
            turnNum=1;
        }
        else if (direction>90 && direction<=180){
            turnNum=3;
        }
        else if (direction>=0 && direction<=90){
            turnNum=2;
        }
        if(getY()<30){
            
            if (turnNum==1||turnNum==3){
                turn(90);
            }
             if (turnNum==2){
                turn(270);
            }
            y=30;
        }    
        if(getX()>780){
            
            if (turnNum==2){
                turn(270);
            }
            if (turnNum==2){
                turn(90);
            }
            x=780;
        }
        if(getY()>580){
            
            if (turnNum==1){
                turn(270);
            }
            if (turnNum==2){
                turn(90);
            }
            y=580;
        }
        if(getX()<30){
            
            if (turnNum==1){
                turn(90);
            }
            if (turnNum==2||turnNum==3){
                turn(270);
            }
            x=30;
        }
        
    }
}  //end class Actor
