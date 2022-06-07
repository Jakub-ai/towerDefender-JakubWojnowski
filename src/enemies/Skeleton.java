package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.SKELETON;
/** klasa sluzaca do okreslenia pol obiektu typu Skeleton **/
public class Skeleton extends Enemy{
    /** konstruktor sluzacy do tworzenia obiektu klasy
     * @param "ID , x, y, em"
     * @return nic nie zwraca **/
    public Skeleton(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, SKELETON, em);

    }
}
