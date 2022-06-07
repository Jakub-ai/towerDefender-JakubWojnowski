package managers;

import enemies.*;
import helpz.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helpz.Constants.Directions.*;
import static helpz.Constants.Enemies.*;
import static helpz.Constants.Tiles.*;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private Enemy testEnemy;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    //private float speed = 0.5f;
    private PathPoint start,end;
    private int HpBarWidth = 20;
    private BufferedImage slowEffect;

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.start = start;
        this.end = end;
        loadEffectImg();
//        addEnemy( GHOST);
//        addEnemy( BAT);
//        addEnemy( SKELETON);
//        addEnemy( KNIGHT);

        enemyImgs = new BufferedImage[4];
       // testEnemy = new Enemy(32*3, 32*9,0,0);


        loadEnemyImgs();
    }

    private void loadEffectImg() {
        slowEffect = LoadSave.getSpriteAtlas().getSubimage(9*32, 3*32,32,32);
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        for(int i = 0; i < 4; i++){
            enemyImgs[i] = atlas.getSubimage(i * 32,32,32,32);

        }
    }

    public void update(){
        for(Enemy e : enemies){
            if(e.isAlive())
                // is next tile road(pos.dir)
                updateEnemyMove(e);

        }

    }



    private void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1)
            setNewDirectionAndMove(e);

        int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir(),e.getEnemyType()));
        int newY =(int)(e.getY() + getSpeedAndHeight(e.getLastDir(),e.getEnemyType()));

        if(getTleType(newX, newY) == ROAD_TILE) {
            //KEEP MOVING in same direction
            e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
        }else if(isAtEnd(e)){
            // reachead the end
          e.kill();
          playing.removeOneLive();


        }else{
            //find new direction
            setNewDirectionAndMove(e);
        }

    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();
        //move into the current tile 100%
        int xCord = (int)(e.getX() / 32);
        int yCord = (int)(e.getY() / 32);

        fixEnemyOffsetTile(e, dir, xCord,yCord);
        if(isAtEnd(e))
            return;
        if(dir == LEFT || dir == RIGHT){
            int newY =(int)(e.getY() + getSpeedAndHeight(UP,e.getEnemyType()));
            if( getTleType((int)e.getX(), newY) == ROAD_TILE)
            e.move(GetSpeed(e.getEnemyType()),UP);
            else
                e.move(GetSpeed(e.getEnemyType()),DOWN);

        }else{
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));
            if(getTleType(newX, (int)e.getY()) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemyType()),RIGHT);
                else
                    e.move(GetSpeed(e.getEnemyType()), LEFT);

        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir){

            case RIGHT:
                if(xCord < 19)
                    xCord++;
                break;
            case DOWN:
                if(yCord < 19)
                    yCord++;
                break;
        }
        e.setPos(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd(Enemy e) {
       if(e.getX() == end.getxCord() * 32)
           if(e.getY() == end.getyCord() * 32)
               return true;
       return false;

    }

    private int getTleType(int x, int y) {
        return playing.getTileType(x,y);
    }



    private float getSpeedAndWidth(int dir, int enemyType) {
        if( dir == LEFT)
            return -GetSpeed(enemyType);
        else if(dir == RIGHT)
            return GetSpeed(enemyType) + 32;
        return 0;
    }
    private float getSpeedAndHeight(int dir,int enemyType){
        if( dir == UP)
            return -GetSpeed(enemyType);
        else if(dir == DOWN)
            return GetSpeed(enemyType) + 32;

        return 0;

    }
    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);

    }

    public void addEnemy( int enemyType){
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;
        switch (enemyType){
            case GHOST:
                enemies.add(new Ghost(x, y,0,this));
                break;
            case KNIGHT:
                enemies.add(new Knight(x, y,0,this));
                break;
            case BAT:
                enemies.add(new Bat(x, y,0,this));
                break;
            case SKELETON:
                enemies.add(new Skeleton(x, y,0,this));
                break;


        }

    }
    public void draw(Graphics g){
        for(Enemy e : enemies) {
            if(e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
                drawEffects(e, g);
            }
        }

    }

    private void drawEffects(Enemy e, Graphics g) {
        if(e.isSlow()){

            g.drawImage(slowEffect, (int)e.getX(), (int)e.getY(), null);
        }
    }


    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.RED);
    g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2 ), (int) e.getY() - 10, getNewBarWidth(e), 3);
    }
    private int getNewBarWidth(Enemy e){
       return (int) (HpBarWidth * e.getHealthBarFloat());

    }

    private void drawEnemy(Enemy e , Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()],(int)e.getX(), (int)e.getY(),null);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    public int getAmountofAliveEnemies() {
        int size = 0;
        for( Enemy e : enemies)
            if(e.isAlive())
                size++;
        return size;
    }

    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }
    public void reset(){
        enemies.clear();
    }
}
