package enemies;

import helpz.Constants;
import managers.EnemyManager;

import java.awt.*;

import static helpz.Constants.Directions.*;
/** klasa Enemy okresla wszystkie charakterystyki obiektu typu Enemy **/
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
/** konstruktor sluzacy do stworzenioa obiektu klasy
 * @param " float x, float y, int ID, int enemyType, EnemyManager enemyManager" **/
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
    /** funkcja okreslajace poziom zycia po stworzeniu obiektu **/
private void setStartHealth(){
        health = Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
}
    /** funkcja dzieki ktorej obiekt otrzymuje obrazenia **/
public void hurt(int dmg){
        this.health -= dmg;
        if(health <= 0) {
            alive = false;
            enemyManager.rewardPlayer(enemyType);
        }



}
    /** funkcja dzieki ktorej obiekt po straceniu wszystki punktow zycia przestaje istniec **/
    public void kill() {
        //for killing at the end

        alive = false;
        health = 0;
        System.out.println("live is lost");

    }
    /** funkcja okresla wartosc poczatkowa slowTick **/
    public void slow() {
        slowTick = 0;
    }
    /** funkcja okreslajaca sposob poruszania sie obiektu oraz kierunki
     * @param  "float speed, int dir
     * @return nic nie zwraca**/
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
/**  funkcja dzieki ktorej aktualizujemy hitBox wraz z ruchem obiektu **/
    private void updateHitBox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }
/** funkcja sluzaca do okreslenia srodka obiektu**/
    public void setPos(int x, int y){
        //to pass the enemy position, this one is for posFix
        this.x = x;
        this.y = y;
    }
    /** funkcja sluzaca do wyswietlania paska zycia
     * @return health / maxHealth **/
    public float getHealthBarFloat(){
        return health / (float) maxHealth;
    }

/** metoda getX() zwraca X
 * @return "x"**/
    public float getX() {
        return x;
    }

    /** metoda getY() zwraca y
     *
     * @return y
     */
    public float getY() {
        return y;
    }

    /** metoda getBounds zwraca ramy kwadratu
     *
     * @return bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /** metoda getHealth zwraca health czyli zycie
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /** metoda getID zwraca id
     *
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /** metoda getEnemyType zwraca typ wroga
     *
     * @return enemyType
     */

    public int getEnemyType() {
        return enemyType;
    }

    /** metoda getLastDir zwraca ostatni ruch obiektu
     *
     * @return lastDir
     */

    public int getLastDir() {
return lastDir;
    }

    /** metoda isAlive zwraca boolean alive true lub false
     *
     * @return alvie
     */
    public boolean isAlive() {
        return alive;
    }

    /** metoda isSlow sprawdza czy obiekt jest pod wplywem spowolnienia
     *
     * @return slowTick < slowTicklimit
     */
    public  boolean isSlow(){
        return slowTick < slowTicklimit;
    }



}
