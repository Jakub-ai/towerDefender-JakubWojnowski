package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.BAT;
/** klasa Bat rozszerzona o klase Enemy sluzaca do tylko do okreslenia pol obiektu typu BAT
        **/
public class Bat extends Enemy{
    /** konstruktor sluzacy do tworzenia obiektu klasy
     * @param "ID , x, y, em"
     * @return nic nie zwraca **/
    public Bat(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, BAT, em);

    }
}
