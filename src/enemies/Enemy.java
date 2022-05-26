package enemies;

import helpz.Constants;
import managers.EnemyManager;

import java.awt.*;

import static helpz.Constants.Directions.*;

public abstract class Enemy {
    protected EnemyManager enemyManager;
    protected float x, y;
    protected Rectangle bounds;
    protected int health;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected int maxHealth;
    protected boolean alive = true;
    protected int slowTicklimit = 120;
    protected  int slowTick = slowTicklimit;

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDir = -1;
        setStartHealth();
    }
private void setStartHealth(){
        health = Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
}
public void hurt(int dmg){
        this.health -= dmg;
        if(health <= 0) {
            alive = false;
            enemyManager.rewardPlayer(enemyType);
        }



}

    public void kill() {
        //for killing at the end

        alive = false;
        health = 0;
        System.out.println("live is lost");

    }
    public void slow() {
        slowTick = 0;



    }

    public void move(float speed, int dir) {
        lastDir = dir;
        if(slowTick < slowTicklimit){
            slowTick++;
            speed *= 0.25f;
        }
       switch(dir){
           case LEFT:
               this.x -=speed;
               break;
           case RIGHT:
               this.x += speed;
               break;
           case UP:
               this.y -= speed;
               break;
           case DOWN:
               this.y += speed;
               break;

       }
       updateHitBox();
    }

    private void updateHitBox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public void setPos(int x, int y){
        //to pass the enemy position, this one is for posFix
        this.x = x;
        this.y = y;
    }
    public float getHealthBarFloat(){
        return health / (float) maxHealth;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }
    public  boolean isSlow(){
        return slowTick < slowTicklimit;
    }



}
