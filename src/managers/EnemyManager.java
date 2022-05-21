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
    private float speed = 0.5f;
    private PathPoint start,end;

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.start = start;
        this.end = end;
        addEnemy( GHOST);
        addEnemy( BAT);
        addEnemy( SKELETON);
        addEnemy( KNIGHT);
        enemyImgs = new BufferedImage[4];
       // testEnemy = new Enemy(32*3, 32*9,0,0);


        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        for(int i = 0; i < 4; i++){
            enemyImgs[i] = atlas.getSubimage(i * 32,32,32,32);

        }
    }

    public void update(){
        for(Enemy e : enemies){
            // is next tile road(pos.dir)
            updateEnemyMove(e);
        }

    }

    private void updateEnemyMove(Enemy e) {
        //e pos
        //e dir
        //tile at new possible pos
        if (e.getLastDir() == -1)
            setNewDirectionAndMove(e);

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir()));
        int newY =(int)(e.getY() + getSpeedAndHeight(e.getLastDir()));

        if(getTleType(newX, newY) == ROAD_TILE) {
            //KEEP MOVING in same direction
            e.move(speed, e.getLastDir());
        }else if(isAtEnd(e)){
            // reachead the end
            System.out.println("lives lost");

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
            int newY =(int)(e.getY() + getSpeedAndHeight(UP));
            if( getTleType((int)e.getX(), newY) == ROAD_TILE)
            e.move(speed,UP);
            else
                e.move(speed,DOWN);

        }else{
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT));
            if(getTleType(newX, (int)e.getY()) == ROAD_TILE)
                e.move(speed,RIGHT);
                else
                    e.move(speed, LEFT);

        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir){
//            case LEFT:
//                if(xCord > 0)
//                    xCord --;
//                break;
//            case UP:
//                if(yCord > 0)
//                    yCord--;
//                break;
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



    private float getSpeedAndWidth(int dir) {
        if( dir == LEFT)
            return -speed;
        else if(dir == RIGHT)
            return speed + 32;
        return 0;
    }
    private float getSpeedAndHeight(int dir){
        if( dir == UP)
            return -speed;
        else if(dir == DOWN)
            return speed + 32;

        return 0;

    }

    public void addEnemy( int enemyType){
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;
        switch (enemyType){
            case GHOST:
                enemies.add(new Ghost(x, y,0));
                break;
            case KNIGHT:
                enemies.add(new Knight(x, y,0));
                break;
            case BAT:
                enemies.add(new Bat(x, y,0));
                break;
            case SKELETON:
                enemies.add(new Skeleton(x, y,0));
                break;


        }

    }
    public void draw(Graphics g){
        for(Enemy e : enemies)
            drawEnemy(e,g);
   // drawEnemy(testEnemy,g);
    }

    private void drawEnemy(Enemy e , Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()],(int)e.getX(), (int)e.getY(),null);
    }
}
