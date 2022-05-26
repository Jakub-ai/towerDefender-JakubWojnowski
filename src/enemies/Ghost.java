package enemies;
import managers.EnemyManager;

import static helpz.Constants.Enemies.GHOST;

public class Ghost extends Enemy {
    public Ghost(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, GHOST,em);

    }
}
