package objects;

import java.awt.geom.Point2D;

/** klasa Projectile zajmuje sie charakterystyka obiektem typu pocisk
 *
 */
public class Projectile {
    private Point2D.Float pos;
    private int id, projectileType, dmg;
    private boolean active = true;
    private float xSpeed, ySpeed, rotation;

    /** konstruktor klasy  Projectile
     *
     * @param x pozycja
     * @param y pozycja
     * @param xSpeed predkosc
     * @param ySpeed predkosc
     * @param dmg wysokosc zadawanych obrazen
     * @param rotation rotacja
     * @param id id obiektu
     * @param projectileType typ obiektu
     */
    public Projectile( float x, float y, float xSpeed, float ySpeed, int dmg, float rotation, int id,int projectileType) {
        pos = new Point2D.Float(x,y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.rotation = rotation;
        this.id = id;
        this.projectileType = projectileType;

    }

    /** metoda reuse jest odpowiedzialna za ponowne uzycie stworzonych obiektow ktore wyszly poza mape
     *
     * @param x  pozycja
     * @param y pozycja
     * @param xSpeed predkosc
     * @param ySpeed predkosc
     * @param dmg wysokosc obrazen
     * @param rotation rotacja
     */
    public void reuse(int x, int y, float xSpeed, float ySpeed, int dmg, float rotation) {
        pos = new Point2D.Float(x,y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.rotation = rotation;
        active = true;

    }

    /** metoda move jest odpowiedzialna za poruszanie sie pocisku
     *
     */
    public void move(){
        pos.x += xSpeed;
        pos.y += ySpeed;


    }

    /** metoda getPos zwraca pozycje obiektu
     *
     * @return pos
     */
    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    /** metoda getId zwraca id obiektu
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /** metoda getProjectileType zwraca typ obiektu
     *
     * @return projectileType
     */
    public int getProjectileType() {
        return projectileType;
    }

    /** metoda getRotation zwraca rotacje
     *
     * @return rotation
     */
    public float getRotation() {
        return rotation;
    }

    /** metoda getDmg zwraca obrazenia
     *
     * @return dmg
     */
    public int getDmg() {
        return dmg;
    }

    /** isActive sprawdza czy obiekt jest aktywny
     *
     * @return false or true
     */
    public boolean isActive() {
        return active;
    }

    /** metoda setActive ustawia status obiektu na aktywny
     *
     * @param active status moze byc true lub false
     */
    public void setActive(boolean active) {
        this.active = active;
    }


}
