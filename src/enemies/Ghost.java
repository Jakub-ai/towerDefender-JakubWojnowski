package enemies;
import managers.EnemyManager;

import static helpz.Constants.Enemies.GHOST;
/** klasa sluzaca do okreslenia pol obiektu typu Ghost **/
public class Ghost extends Enemy {
    /** konstruktor sluzacy do tworzenia obiektu klasy
     * @param "ID , x, y, em"
     * @return nic nie zwraca **/
    public Ghost(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, GHOST,em);

    }
}
