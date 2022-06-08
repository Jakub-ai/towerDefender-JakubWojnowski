package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;

/** klasa ProjectileManager w ktorej znajduja sie wszystkie metody zwiazane z pociskami
 *
 */
public class ProjectileManager {

    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private BufferedImage[] projImgs, exploImgs;
    private int projId = 0;

    /** kontruktor klasy ProjectileManager
     *
     * @param playing
     */
    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();

    }

    /**
     * metoda importImgs importuje textury pociskow do tablicy projImgs
     * @return nic nie zwraca
     */
    public void importImgs(){
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projImgs = new BufferedImage[4];
        projImgs[2] = atlas.getSubimage(4*32,2*32,32,32);
        projImgs[3] = atlas.getSubimage(9*32,2*32,32,32);

        for (int i = 0; i < 2; i++)
            projImgs[i] = atlas.getSubimage((8 + i) * 32, 32, 32, 32);

        importExplosion(atlas);

    }

    /**
     * metoda importExplosion importuje textury wybuchow do tablicy exploImgs
     * @param atlas
     * @return nic nie zwraca
     */
    private void importExplosion(BufferedImage atlas) {
        exploImgs = new BufferedImage[7];
        //exploImgs[7] = atlas.getSubimage(9*32, 3*32,32,32);
        for(int i = 0; i < 7; i++){
            exploImgs[i] = atlas.getSubimage(i * 32, 3*32, 32,32);
        }
    }

    /** metoda newProjectile
     * tworzy obiekt typu pocisk, zajmuje sie jego mechanika dla poszczegolnego rodzaju pocisku
     * w tym przypadku pocisk rakieta oraz strzala obracaja sie w strone obiektu typu Enemy
     * okresla predkosc
     * @param t
     * @param e
     */
    public void newProjectile(Tower t, Enemy e){
        int type = getProjType(t);


        int xDist = (int) (t.getX() - e.getX());
        int yDist = (int) (t.getY() - e.getY());
        int totalDist =  Math.abs(xDist) + Math.abs(yDist);
        float xPer = (float)Math.abs(xDist) / totalDist;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;
        if(t.getX() > e.getX())
            xSpeed *= -1;
        if(t.getY() > e.getY())
            ySpeed *= -1;

        float rotate = 0;

        if(type == Arrow || type == MISSLE) {
            float arcValue = (float) Math.atan(yDist / (float) xDist);
            rotate = (float) Math.toDegrees(arcValue);

            if (xDist < 0)
                rotate += 180;
        }




        for(Projectile p : projectiles)
            if(!p.isActive())
                if(p.getProjectileType() == type){
                    p.reuse(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDamage(), rotate);
                    return;
                }
        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDamage(), rotate , projId++, type));



    }


    /** metoda update aktualizuje pozycje pocisku i likwiduje pocisk kiedy jest poza mapa dla lepszej optymalizacji gry
     * @return nic nie zwraca
     */
    public void update(){
        for(Projectile p : projectiles)
            if(p.isActive()){
                p.move();
                if(isProjHittingEnemy(p)){
                    p.setActive(false);
                    if(p.getProjectileType() == BOMB || p.getProjectileType() == MISSLE)
                        explosions.add(new Explosion(p.getPos()));
                        explodeOnEnemies(p);

                }
            }else if(isProjOutsideBounds(p)){
                p.setActive(false);

            }
        for (Explosion e : explosions)
            if (e.getExploIndex() < 7)
            e.update();

    }

    /** metoda explodeOnEnemies zajmuje jest odpowiedzialne za efekt wybuchu w momencie kolizji pbiektu pocisk z obiektem wrog
     * dodatkowo gdy inne obiekt sa w zasiegu wybuchu otrzymuja obrazenia
     *
     * @param p
     */
    private void explodeOnEnemies(Projectile p) {
        for( Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                float radius = 40.0f;
                float xDist = Math.abs(p.getPos().x - e.getX());
                float yDist = Math.abs(p.getPos().y - e.getY());

                float realDist = (float) Math.hypot(xDist, yDist);

                if (realDist <= radius)
                    e.hurt(p.getDmg());
            }
        }
    }

    /** metoda isProjOutsideBounds sprawdza czy obiekt pocisku jest poza mapa
     *
     * @param p
     * @return true or false
     */
    private boolean isProjOutsideBounds(Projectile p) {
        if(p.getPos().x >= 0)
            if(p.getPos().x <= 640)
                if(p.getPos().y >= 0)
                    if(p.getPos().y <= 800)
                        return false;
        return true;

    }

    /** metoda isProjHittingEnemy sprawdza czy pocisk ma kolizje z obiektem wrog
     *
     * @param p
     * @return true or false
     */
    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (e.getBounds().contains(p.getPos())) {
                    e.hurt(p.getDmg());
                    if(p.getProjectileType() == CHAINS)
                        e.slow();
                    return true;
                }
            }
        }
            return false;
        }

    /** metoda draw wyswietla pociski na mapie oraz zajmuje sie obrotem pociskow arrow i missle
     *
     * @param g
     * @return nic nie zwraca
     */
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;


        for(Projectile p : projectiles)
            if(p.isActive()){
                if(p.getProjectileType()==Arrow || p.getProjectileType()==MISSLE ){
                g2d.translate(p.getPos().x,p.getPos().y);
                g2d.rotate(Math.toRadians(p.getRotation()));
                g2d.drawImage(projImgs[p.getProjectileType()],-16,- 16,null );
                g2d.rotate(-Math.toRadians(p.getRotation()));
                g2d.translate(-p.getPos().x,-p.getPos().y);

            }else {
                    g2d.drawImage(projImgs[p.getProjectileType()],(int)p.getPos().x-16,(int)p.getPos().y-16,null );

                }
                drawExplosions(g2d);
                }




    }

    /** metoda drawExplosions wyswietla efekt eksplozi
     *
     * @param g2d
     * @return nic nie zwraca
     */
    private void drawExplosions(Graphics2D g2d) {
        for (Explosion e : explosions)
            if(e.getExploIndex() < 7)
                g2d.drawImage(exploImgs[e.getExploIndex()], (int)e.getPos().x-16, (int)e.getPos().y-16, null);
    }

    /**
     * getProjType metoda zwraca typ pociskow odpowiedni dla towera
     * @param t
     * @return arrow or bomb or missle or chains or 0
     */
    private int getProjType(Tower t) {
        switch (t.getTowerType()) {
            case ARCHER:
                return Arrow;
            case CANNON:
                return BOMB;
            case BAZOOKA:
                return MISSLE;
            case MAGE:
                return CHAINS;
        }
        return 0;
    }

    /** sub -klasa Explosion sluzy do animacji eksplozji
     *
     */
    public class Explosion{
        private Point2D.Float pos;
        private int exploTick = 0, exploIndex = 0;

        /** konstruktor klasy Explosion
         *
         * @param pos
         */
        public Explosion(Point2D.Float pos) {
            this.pos = pos;

        }

        /** metoda update aktualizuja animacje eksplozji
         * @return "nic nie zwraca"
         */
        public void update(){

                exploTick++;
                if(exploTick >= 12){
                    exploTick = 0;
                    exploIndex++;


                    }
                }

        /** metoda getExploIndex zwraca index animacji explozji
         *
         * @return exploIndex
         */
        public int getExploIndex(){
            return exploIndex;
        }

        /** metoda getPos zwraca pozycje explozji
         *
          * @return pos
         */
        public Point2D.Float getPos(){
            return pos;
        }

    }

    /** metoda reset resetuje wszystko
     * @return nic nie zwraca
     */
    public void reset(){
        projectiles.clear();
        explosions.clear();
        projId = 0;
    }

    }

