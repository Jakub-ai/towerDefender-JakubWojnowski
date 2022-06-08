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

/**
 * klasa EnemyManager w sa wszelkie mechaniki zwiazane z obiektem typu Enemy
 */
public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private Enemy testEnemy;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    //private float speed = 0.5f;
    private PathPoint start,end;
    private int HpBarWidth = 20;
    private BufferedImage slowEffect;

    /** konstruktor klasy Enemy inicjuje obiekt Enemy
     *
     * @param playing
     * @param start
     * @param end
     */
    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.start = start;
        this.end = end;
        loadEffectImg();


        enemyImgs = new BufferedImage[4];



        loadEnemyImgs();
    }

    /** metoda loadEffectImg pobiera texture ognia dla efektu slow ze spriteAtlas
     * @return nic nie zwraca
     */
    private void loadEffectImg() {
        slowEffect = LoadSave.getSpriteAtlas().getSubimage(9*32, 3*32,32,32);
    }

    /** metoda pobiera textury dla obiektu enemy z spriteAtlas
     * @return nic nie zwraca
     */
    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        for(int i = 0; i < 4; i++){
            enemyImgs[i] = atlas.getSubimage(i * 32,32,32,32);

        }
    }

    /** metoda aktualizuje poruszanie sie Enemy
     * @return nic nie zwraca
     */
    public void update(){
        for(Enemy e : enemies){
            if(e.isAlive())
                // is next tile road(pos.dir)
                updateEnemyMove(e);

        }

    }

    /**
     * metoda updateEnemyMove sluzy do poruszania sie obiektu typu enemy po drodze
     * nastepnie jesli obiekt dojdzie do celu przestaje istniec
     *
     * @param e
     */

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

    /**
     * metoda setNewDirectionAndMove sluzy do szukania przez obiekt drogi po ktorej ma przejsc.
     * obiekt przejdzie jedynie w sciezka typu Road_tile. W momencie kiedy droga zmienia kierunek prawo lewo etc
     * znajdzie nowy kierunek
     * @param e
     */
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

    /**
     * metoda fixEnemyOffsetTile wysrodkowuje punkt obiektu.
     * @param e
     * @param dir
     * @param xCord
     * @param yCord
     * @return nic nie zwraca
     */
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

    /**
     * metoda isAtEnd sprawdza czy obiekt doszedl do punktu konca
     * @param e
     * @return true lub false
     */
    private boolean isAtEnd(Enemy e) {
       if(e.getX() == end.getxCord() * 32)
           if(e.getY() == end.getyCord() * 32)
               return true;
       return false;

    }

    /**
     * metoda zwraca typ textury
     * @param x
     * @param y
     * @return playing.getTileType(x,y);
     */
    private int getTleType(int x, int y) {
        return playing.getTileType(x,y);
    }


    /** metoda getSpeedAndWidth okresla predkosc i w jaki sposob obiekt porusza sie w prawo lewo
     *
     * @param dir
     * @param enemyType
     * @return GetSpeed(enemyType) + 32 or -GetSpeed(enemyType) or 0
     */
    private float getSpeedAndWidth(int dir, int enemyType) {
        if( dir == LEFT)
            return -GetSpeed(enemyType);
        else if(dir == RIGHT)
            return GetSpeed(enemyType) + 32;
        return 0;
    }

    /**
     * metoda getSpeedAndHeight okresla predkosc i w jaki sposob obiekt porusza sie gora dol
     * @param dir
     * @param enemyType
     * @return -GetSpeed(enemyType) or GetSpeed(enemyType) + 32 or 0
     */
    private float getSpeedAndHeight(int dir,int enemyType){
        if( dir == UP)
            return -GetSpeed(enemyType);
        else if(dir == DOWN)
            return GetSpeed(enemyType) + 32;

        return 0;

    }

    /** metoda spawnEnemy dodaje obiekt typu enemy
     *
     * @param nextEnemy
     * @return nic nie zwraca
     */
    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);

    }

    /** metoda addEnemy okresla jaki przeciwnik bedzie odawany
     *
     * @param enemyType
     */
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

    /** metoda draw sluzy do wyswietlania obiektow
     *
     * @param g
     * @return nic nie zwraca
     */
    public void draw(Graphics g){
        for(Enemy e : enemies) {
            if(e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
                drawEffects(e, g);
            }
        }

    }

    /**
     * metoda drawEffects rysuje efekt ognia kiedy obiekt enemy jest podwplywem efektu slow
     * @param e
     * @param g
     * @return nic nie zwraca
     */
    private void drawEffects(Enemy e, Graphics g) {
        if(e.isSlow()){

            g.drawImage(slowEffect, (int)e.getX(), (int)e.getY(), null);
        }
    }

    /** metoda drawHealthBar wyswietla healthBar nad obiektem typu Enemy
     *
     * @param e
     * @param g
     * @return nic nie zwraca
     */
    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.RED);
    g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2 ), (int) e.getY() - 10, getNewBarWidth(e), 3);
    }

    /**
     * metoda getNewBarWidth okresla dlugosc healthBar
     * @param e
     * @return
     */
    private int getNewBarWidth(Enemy e){
       return (int) (HpBarWidth * e.getHealthBarFloat());

    }

    /**
     * metoda drawEnemy wyswietla obiekt typu enemy
     * @param e
     * @param g
     * @return nic nie zwraca
     */
    private void drawEnemy(Enemy e , Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()],(int)e.getX(), (int)e.getY(),null);
    }

    /** tablica getEnemies ktora zwraca obiekty typu Enemies
     *
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /** metoda getAmountofAliveEnemies zlicza aktywne obiekty typu enemy
     *
     * @return size
     */

 public int getAmountofAliveEnemies() {
        int size = 0;
        for( Enemy e : enemies)
            if(e.isAlive())
                size++;
        return size;
    }

    /** metoda rewardPlayer metoda zwraca wysokosc nagrody dla gracza za pokonania przeciwnika
     *
     * @param enemyType
     */
    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }

    /** metoda reset resetuje cala sekwencje
     *
     */
    public void reset(){
        enemies.clear();
    }
}
