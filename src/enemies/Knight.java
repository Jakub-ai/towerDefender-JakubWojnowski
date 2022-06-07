package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.KNIGHT;
/** klasa sluzaca do okreslenia pol obiektu typu Knight **/
public class Knight extends Enemy{
    /** konstruktor sluzacy do tworzenia obiektu klasy
     * @param "ID , x, y, em"
     * @return nic nie zwraca **/
    public Knight(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, KNIGHT, em);
    }
}
