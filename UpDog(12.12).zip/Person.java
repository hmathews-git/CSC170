import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor
{
    private boolean balloonReady = false;
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        handleMovement();
    }
    private void handleMovement() {
        int newX = getX();
        
        if (Greenfoot.isKeyDown("a")) newX -= 4;
        if (Greenfoot.isKeyDown("d")) newX += 4;
        
        World w = getWorld();
        if (w != null) {
            newX = Math.max(0, Math.min(newX, w.getWidth() - 1));
        }
        setLocation(newX, getY());
    }
    public void giveBalloon() {
        balloonReady = true;
    }
    public void consumeBalloon() {
        balloonReady = false;
    }
    public boolean hasBalloon() {
        return balloonReady;
    }
    
}
