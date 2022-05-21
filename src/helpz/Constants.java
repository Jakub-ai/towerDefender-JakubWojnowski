package helpz;

public class Constants {
    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int MAGE = 2;
        public static final int BAZOOKA = 3;
    }
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }
    public static class Enemies{
        public static final int GHOST = 0;
        public static final int KNIGHT = 1;
        public static final int BAT = 2;
        public static final int SKELETON = 3;

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


    }
}
