package helpz;

public class Constants {
    public static class Projectiles{
        public static final int Arrow = 0;
        public static final int BOMB = 1;
        public static final int MISSLE = 2;
        public static final int CHAINS = 3;
        public static float GetSpeed (int type){
            switch (type){
                case Arrow:
                    return 3f;
                case  BOMB:
                    return 1f;
                case MISSLE:
                    return 2f;
                case CHAINS:
                    return 4f;
            }
            return 0f;
        }

    }
    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int MAGE = 2;
        public static final int BAZOOKA = 3;

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
        public static int GetStartDamage(int towerType){
            switch (towerType){
                case CANNON:
                    return 25;
                case ARCHER:
                    return 1;
                case MAGE:
                    return 40;
                case BAZOOKA:
                    return 80;
            }
            return 0;

        }
        public static float GetDefaultRange(int towerType){
            switch (towerType){
                case CANNON:
                    return 100;
                case ARCHER:
                    return 100;
                case MAGE:
                    return 101;
                case BAZOOKA:
                    return 104;
            }
            return 0;

        }
        public static float GetDefaultCoolDown(int towerType){
            switch (towerType){
                case CANNON:
                    return 10;
                case ARCHER:
                    return 11;
                case MAGE:
                    return 12;
                case BAZOOKA:
                    return 13;
            }
            return 0;
        }

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


        public static int GetStartHealth(int enemyType) {
            switch(enemyType){
                case GHOST:
                    return 100 ;
                case KNIGHT:
                    return 300;
                case BAT:
                    return  50;
                case SKELETON:
                    return 80;
            }
            return 0;        }
    }
}
