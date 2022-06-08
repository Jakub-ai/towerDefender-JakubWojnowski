package objects;

import helpz.Constants;

import static helpz.Constants.Towers.*;

/** klasa Tower zajmuje sie charakterystyka obiektu tower
 *
 */
public class Tower {
    private int x, y, id, towerType,damage, cdTick;
    private float  range, coolDown;
    private int tier;

    /** kosntruktor klasy Tower
     *
     * @param x pozycja
     * @param y pozycja
     * @param id id obiektu
     * @param towerType rodzaj obiektu
     */
    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        tier = 1;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCoolDown();

    }

    /** metoda update aktualizuje czas
     *
     */
    public void update(){
        cdTick++;
    }

    /** metoda upgradeTower jest odpowiedzialna za upgrade obiektu
     * w samej metodzie sa ustalone o ile punktow sie zwieksza dana statystyka
     */
    public void upgradeTower(){
        this.tier++;
        switch (towerType){
            case CANNON:
                damage += 5;
                range += 15;
                coolDown -= 5;
            case ARCHER:
                damage += 3;
                range += 10;
                coolDown -= 5;
                break;
            case MAGE:
                range += 10;
                coolDown -= 5;
                break;
            case BAZOOKA:
                damage += 5;
                range += 10;
                coolDown -= 2;
                break;
        }

    }

    /** metoda isCoolDownOver sprawdza czy cooldown miedzy strzalami sie skonczyl
     *
     * @return cdTick >= coolDown
     */
    public boolean isCoolDownOver() {
        return cdTick >= coolDown;
    }

    /** metedo resetCoolDown reseturje cooldwon
     *
     */
    public void resetCoolDown() {
        cdTick = 0;
    }

    /** metoda setDefaultRange ustala poczatkowy zasie wiezy
     *
     */
    private void setDefaultRange() {
        range = Constants.Towers.GetDefaultRange(towerType);


    }

    /** metoda setDefaultDmg ustala poczatkowe obrazenia wiezy
     *
     */
    private void setDefaultDmg() {
        damage = Constants.Towers.GetStartDamage(towerType);


    }

    /** metoda setDefaultCoolDown ustala poczatkowy cooldwon
     *
     */
    private void setDefaultCoolDown() {
       coolDown = Constants.Towers.GetDefaultCoolDown(towerType);
    }

    /** metoda getX zwraca x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /** metoda getY zwraca y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /** metoda getId zwraca id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /** metoda getTowerType zwraca typ wiezy
     *
     * @return towerType
     */
    public int getTowerType() {
        return towerType;
    }

    /** metoda getDamage zwraca obrazenia
     *
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /** metoda getRange zwraca zasieg
     *
     * @return range
     */
    public float getRange() {
        return range;
    }

    /** metoda getCoolDown zwraca cooldown
     *
     * @return coolDown
     */
    public float getCoolDown() {
        return coolDown;
    }

    /** metoda getTier zwraca tier czyli poziom obiektu tower
     *
     * @return tier
     */
    public int getTier(){
        return tier;
    }


}

