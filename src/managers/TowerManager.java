package managers;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Towers.*;

/** klasa tower manager w ktorej znajduja sie wszystkie metody zwiazane z mechanika obiektu typu tower
 *
  */
public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImgs();

    }

    /** loadTowerImgs w tej metodzie sa pobierane sa pobierane z atlasu textury obiektow typu tower i umieszczane w tablicy towerimgs
     *
     */
    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[4];
        towerImgs[3] = atlas.getSubimage(5 * 32, 2*32, 32,32);
        for(int i = 0; i < 3; i++)
            towerImgs[i] = atlas.getSubimage((5 + i) * 32, 32,32,32);
    }

    /** addTower w tej metodzie obiekty tyu tower beda przechowywane w ArrayList
     *
     * @param selectedTower tu jest pobierany typ obiektu
     * @param xPos pozycja obiektu
     * @param yPos pozycja obiektu
     */
    public void addTower(Tower selectedTower, int xPos, int yPos) {

        towers.add(new Tower(xPos,yPos,towerAmount++,selectedTower.getTowerType()));


    }

    /** metoda removeTower sluzy do usuniecia ustawionego obiektu typu tower
     *
     * @param displayedTower jest to postawiony na mapie obiekt typu tower
     */
    public void removeTower(Tower displayedTower) {
        for(int i =0; i < towers.size();i++){
            if(towers.get(i).getId() == displayedTower.getId())
                towers.remove(i);
        }


    }

    /** upgradeTower jest to metoda dzieki ktorej pobierane jest id postawionego obiektu typu tower dzieki czemu mozna upgradowac obiekt
     * jesli jest to mozliwe
     * @param displayedTower
     *
     */
    public void upgradeTower(Tower displayedTower) {
        for(Tower t : towers)
            if(t.getId() == displayedTower.getId())
                t.upgradeTower();


    }

    /** metoda update zajmuje sie aktualizacja sekwencji i sprawdza czy enemy jest w zasiegu tower
     *
     */
    public void update(){
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }

    }

    /** metoda boolean isEnemyInRange sprawdza czy enemy jest w zasiegu za pomoca dzialania matematycznego GetHypoDistance
     *
     * @param t tower
     * @param e enemy
     * @return "range < t.getRange() "
     */
    private Boolean isEnemyInRange(Tower t, Enemy e){
        int range = helpz.Utilz.GetHypoDistance(t.getX(),t.getY(),e.getX(),e.getY());
        return range < t.getRange();

    }

    /** attackEnemyIfClose ta metoda jest odspowiedzialna za mechanike ataku obiektu typu tower na enemy
     * jest tutja sprawdzenie czy enemy jest w zasiegu
     * jest tutaj takze cooldown
     * @param t tower
     */
    private void attackEnemyIfClose(Tower t) {

        for (Enemy e : playing.getEnemyManager().getEnemies()){
            if(e.isAlive())
            if(isEnemyInRange(t, e)){
                if(t.isCoolDownOver()) {
                    playing.shootEnemy(t, e);
                    t.resetCoolDown();
                }
                //shoot enemy

            }else{
                //no shooting
            }
        }

        }

    /** metoda draw jest odpowiedzialna za wyswietlanie obiektu na mapie
     *
     * @param g
     */
    public void draw(Graphics g){
        for (Tower t : towers)
            g.drawImage(towerImgs[t.getTowerType()],t.getX(),t.getY(),null );

    }

    /** metoda getTowerImgs zwraca tablice towerImgs
     *
     * @return towerImgs
     */
    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    /** getTowerAt sprawdza koordynaty tower na mapie
     *
     * @param x pozycja
     * @param y pozycja
     * @return t tower lub null
     */
    public Tower getTowerAt(int x, int y){
        for(Tower t : towers)
            if(t.getX() == x)
                if(t.getY() == y)
                    return t;
        return null;
    }

    /** metoda reset resetuje cala sekwencje
     *
     */
    public void reset(){
        towers.clear();
        towerAmount = 0;

}

}
