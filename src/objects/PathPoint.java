package objects;

/** klasa Path point jest oddpowiedzialna za obiekt typu pathpoint jest ich dokladnie dwa
 *
 */
public class PathPoint {
    private int xCord, yCord;

    /** konstruktor klasy PathPoint
     *
     * @param xCord wspolrzedne
     * @param yCord wspolrzedne
     */
    public PathPoint(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    /** metoda getxCord zwraca xCord wspolrzedne
     *
     * @return xCord
     */
    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    /** metoda getyCord zwraca wspolrzedne yCord
     *
     * @return yCord
     */
    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }
}