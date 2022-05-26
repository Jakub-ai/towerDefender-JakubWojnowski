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

public class ProjectileManager {

    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private BufferedImage[] projImgs, exploImgs;
    private int projId = 0;


    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();

    }



    public void importImgs(){
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projImgs = new BufferedImage[4];
        projImgs[2] = atlas.getSubimage(4*32,2*32,32,32);
        projImgs[3] = atlas.getSubimage(9*32,2*32,32,32);

        for (int i = 0; i < 2; i++)
            projImgs[i] = atlas.getSubimage((8 + i) * 32, 32, 32, 32);

        importExplosion(atlas);

    }

    private void importExplosion(BufferedImage atlas) {
        exploImgs = new BufferedImage[7];
        //exploImgs[7] = atlas.getSubimage(9*32, 3*32,32,32);
        for(int i = 0; i < 7; i++){
            exploImgs[i] = atlas.getSubimage(i * 32, 3*32, 32,32);
        }
    }

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
    private boolean isProjOutsideBounds(Projectile p) {
        if(p.getPos().x >= 0)
            if(p.getPos().x <= 640)
                if(p.getPos().y >= 0)
                    if(p.getPos().y <= 800)
                        return false;
        return true;

    }
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

    private void drawExplosions(Graphics2D g2d) {
        for (Explosion e : explosions)
            if(e.getExploIndex() < 7)
                g2d.drawImage(exploImgs[e.getExploIndex()], (int)e.getPos().x-16, (int)e.getPos().y-16, null);
    }

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

    public class Explosion{
        private Point2D.Float pos;
        private int exploTick = 0, exploIndex = 0;
        public Explosion(Point2D.Float pos) {
            this.pos = pos;

        }
        public void update(){

                exploTick++;
                if(exploTick >= 12){
                    exploTick = 0;
                    exploIndex++;


                    }
                }

        public int getExploIndex(){
            return exploIndex;
        }
        public Point2D.Float getPos(){
            return pos;
        }

    }
    public void reset(){
        projectiles.clear();
        explosions.clear();
        projId = 0;
    }

    }

