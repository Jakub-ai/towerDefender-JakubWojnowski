package helpz;
/** klasa w ktorej mam inne wewnetrzne klasy statyczne tworzenia obiektow o okreslonej charakterystyce innych obiektow  **/
public class Constants {
    /** klasa Projectiles okresla nam id danego typu obiektu Arrow, BOMB etc. oraz predkosc danego obiektu ktory jest pociskiem **/
    public static class Projectiles{
        public static final int Arrow = 0;
        public static final int BOMB = 1;
        public static final int MISSLE = 2;
        public static final int CHAINS = 3;

        /** metoda GetSpeed ktora zwraca predkosc obiektu pocisk
         *
         * @param type
         * @return 8f, 4f, 10f, 6f
         */
        public static float GetSpeed (int type){

            switch (type){
                case Arrow:
                    return 8f;
                case  BOMB:
                    return 4f;
                case MISSLE:
                    return 10f;
                case CHAINS:
                    return 6f;
            }
            return 0f;
        }

    }
    /** Towers to klasa sluzaca do tworzenia obiektow typu towers takich jak CANNON czy ARCHER **/
    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int MAGE = 2;
        public static final int BAZOOKA = 3;
/** metoda GetTowerCost zwraca koszt kupienia obiektu typu Tower podczas rozgrywki
 * @return 65, 20, 45, 80 **/
        public static int GetTowerCost(int towerType){
            switch (towerType){
                case CANNON:
                    return 65;
                case ARCHER:
                    return 20;
                case MAGE:
                    return 45;
                case BAZOOKA:
                    return 80;
            }
            return 0;

        }
        /** metoda GetName zwracajaca String nazwe obiektu typu tower
         * @return CANNON, ARCHER, MAGE, BAZOOKA **/
        public static String GetName(int towerType){
            switch (towerType){
                case CANNON:
                    return "CANNON";
                case ARCHER:
                    return "ARCHER";
                case MAGE:
                    return "MAGE";
                case BAZOOKA:
                    return "BAZOOKA";
            }
            return "";
        }
        /** metoda GetStartDamage zwraca nam obrazenia startowe obiektu typu tower
         * @return 40, 15, 5, 10, 0 **/
        public static int GetStartDamage(int towerType){
            switch (towerType){
                case CANNON:
                    return 40;
                case ARCHER:
                    return 15;
                case MAGE:
                    return 5;
                case BAZOOKA:
                    return 10;
            }
            return 0;

        }
        /** metoda GetDefaultRange zwraca nam zasieg startowy obiektu typu tower
         * @return 200, 125, 130, 150, 0 **/
        public static float GetDefaultRange(int towerType){
            switch (towerType){
                case CANNON:
                    return 200;
                case ARCHER:
                    return 125;
                case MAGE:
                    return 130;
                case BAZOOKA:
                    return 150;
            }
            return 0;

        }
        /** metoda GetDefaultCoolDown zwraca nam cooldown obiektu. cooldow sluzy do okreslenia czasu pomiedzy wystrzelonymi pociskami
         * @return 120, 20, 35, 25, 0 **/
        public static float GetDefaultCoolDown(int towerType){
            switch (towerType){
                case CANNON:
                    return 120;
                case ARCHER:
                    return 20;
                case MAGE:
                    return 35;
                case BAZOOKA:
                    return 25;
            }
            return 0;
        }

    }
    /** statyczna klasa Directions okresla id kierunku takich jak LEFT = 0 czy DOWN = 3**/

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
    /** statyczna klasa Tiles okresla id dla textury typu WATER_TILE, GRASS_TILE czy ROAD_TILE sluzy to glownie do okreslenia drogi dla obiektow typu Enemy
     * oraz miejsca ustawienia obiektu typu Tower**/
    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }
    /** statyczna klasa Enemies okresla nam charakterystyke obiektu Enemy **/
    public static class Enemies{
        public static final int GHOST = 0;
        public static final int KNIGHT = 1;
        public static final int BAT = 2;
        public static final int SKELETON = 3;
/** metoda GetReward zwraca nam wysokosc nagrody dla gracza za pokonanie obiektu typu Enemy
 * @return 10 ,30, 5, 20 , 0 **/
        public static int GetReward(int enemyType){

            switch(enemyType){
                case GHOST:
                    return 10 ;
                case KNIGHT:
                    return 30;
                case BAT:
                    return  5;
                case SKELETON:
                    return 20;
            }
            return 0;

        }
/** metoda GetSpeed zwraca predkosc poruszania sie obiektu po mapie podczas rozgrywki
 * @return 0.5f,0.3f, 0.8f, 0.65f   **/
        public static float GetSpeed(int enemyType){
            switch(enemyType){
                case GHOST:
                    return 0.5f ;
                case KNIGHT:
                    return 0.3f;
                case BAT:
                    return  0.8f;
                case SKELETON:
                    return 0.65f;
            }
            return 0;
        }

/** statyczna metoda GetStartHealth zwraca wysokosc punktow zycia obiektu typu Enemy
 * @return 300,800, 100, 600, 0 **/
        public static int GetStartHealth(int enemyType) {
            switch(enemyType){
                case GHOST:
                    return 300 ;
                case KNIGHT:
                    return 800;
                case BAT:
                    return  100;
                case SKELETON:
                    return 600;
            }
            return 0;        }
    }
}
