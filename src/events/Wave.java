package events;

import java.util.ArrayList;
/** klasa okresla eventu Wave dzieki ktoremu pojawiaja sie fale obiektow typu enemy **/
public class Wave {
    private ArrayList<Integer> enemyList;

    /** konstruktor sluzacy do stworzenioa obiektu klasy ktorym jest event
     *
     * @param enemyList
     */
    public Wave(ArrayList<Integer> enemyList) {
        this.enemyList = enemyList;
    }

    /** metoda getEnemyList zwraca ArrayList enemy
     *
     * @return enemyList
     */
    public ArrayList<Integer> getEnemyList() {
        return enemyList;
    }
}
