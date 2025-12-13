import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dog extends Actor
{
    private int fallSpeed = 2;
    private int floatSpeed = -2;
    
    private boolean saved = false;
    private boolean handledCollision = false;
    
    public void act()
    {
        if (!saved) {
            fall();
            checkCaught();
            checkMissed();
        }
        else {
            floatUp();
            checkOffTop();
        }
    }
    
    private void fall()
    {
        setLocation(getX(),getY() + fallSpeed);
    }
    
    private void floatUp() {
        setLocation(getX(),getY() + floatSpeed);
    }
    
    private void checkCaught()
    {
        if (handledCollision) return;
        
        if (isTouching(Person.class)) {
            Person p = (Person) getOneIntersectingObject(Person.class);
            UpdogWorld w = (UpdogWorld) getWorld();
            
            handledCollision = true;
            
            if (p != null && p.hasBalloon()) {
                p.consumeBalloon();
                if (w != null) w.addScore(1);

                saved = true;
                return;
            }
            else {
                if (w!= null) w.dogMissed();
                if (w != null) w.removeObject(this);
                return;
            }
        }
    }
    
    private void checkMissed() 
    {
        World w = getWorld();
        if (w != null && getY() >= w.getHeight() -1) {
            if (w instanceof UpdogWorld) {
                ((UpdogWorld) w).dogMissed();

            }
            w.removeObject(this);
        }
    }
    
    private void checkOffTop() {
        World w = getWorld();
        if (w != null && getY() <= 0) {
            w.removeObject(this);
        }
    }
    
}
