import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpdogWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpdogWorld extends World
{
    private int score = 0;
    private int dogSpawnTimer = 0;
    private int balloonSpawnTimer = 0;
    
    private int misses = 0;
    private static final int MAX_MISSES = 3;
    
    private Person player;
    
    private long startTimeMs;
    private static final long WIN_TIME_MS = 60_000;
    
    private boolean gameOver = false;
    
    public UpdogWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
        
        startTimeMs = System.currentTimeMillis();
        showHUD();
    }
    public void act() {
        if (gameOver) return;
        
        long elapsed = System.currentTimeMillis() - startTimeMs;
        if (elapsed >= WIN_TIME_MS) {
            winGame();
            return;
        }
        
        dogSpawnTimer++;
        
        if (dogSpawnTimer >= 180) {
            spawnDog();
            dogSpawnTimer = 0;
        }
        
        balloonSpawnTimer++;
        if (balloonSpawnTimer >= 90) {
            spawnBalloonIfNeeded();
            balloonSpawnTimer = 0;
        }
        
        showHUD();
    }
        private void prepare() {
        player = new Person();
        addObject(player, getWidth() / 2, getHeight() - 50);
        
        addObject(new Dog(),getWidth() / 2, 0);
        spawnBalloonIfNeeded();
    }
    private void spawnDog() {
        int x = Greenfoot.getRandomNumber(getWidth());
        addObject(new Dog(),x,0);
    }
    private void spawnBalloonIfNeeded() {
        if (player != null && player.hasBalloon()) return;
        if (!getObjects(Balloon.class).isEmpty()) return;
        
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = getHeight() - 30;
        addObject(new Balloon(), x, y);
    }
    public void addScore(int amount) {
        score += amount;
    }
    public void dogMissed() {
        if (gameOver) return;
        
        misses++;
        if (misses >= MAX_MISSES) {
            loseGame();
        }
    }
    private void showHUD() {
        showText("Score: " + score, 80, 20);
        
        if (player != null & player.hasBalloon()) {
            showText("Balloon: READY", 110, 40);
        }
        else {
            showText("Balloon: NONE", 110, 40);
        }
        
        showText("Misses: " + misses + "/" + MAX_MISSES, 95,60);
        
        long elapsed = System.currentTimeMillis() - startTimeMs;
        long remainingMs = Math.max(0, WIN_TIME_MS - elapsed);
        int remainingSec = (int)(remainingMs / 1000);
        showText("Time Left: " + remainingSec + "s", 110, 80);
    }
    private void winGame() {
        gameOver = true;
        showText("CONGRATS! you saved the dogs!",getWidth()/2, getHeight()/2);
        Greenfoot.stop();
    }
    private void loseGame() {
        gameOver = true;
        showText("GAME OVER! You dropped 3 dogs.", getWidth()/2,getHeight()/2);
        Greenfoot.stop();
    }
    /**
     * Constructor for objects of class UpdogWorld.
     * 
     */
    
}
