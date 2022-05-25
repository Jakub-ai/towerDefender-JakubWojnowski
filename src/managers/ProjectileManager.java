package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;

public class ProjectileManager {

    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projImgs;
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


        float arcValue = (float) Math.atan(yDist / (float) xDist);
        float rotate = (float) Math.toDegrees(arcValue);

        if(xDist < 0)
            rotate += 180;



        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDamage(), rotate , projId++, type));



    }



    public void update(){
        for(Projectile p : projectiles)
            if(p.isActive()){
                p.move();
                if(isProjHittingEnemy(p)){
                    p.setActive(false);

                }else{
                    //nothing happen
                }
            }


    }

    private boolean isProjHittingEnemy(Projectile p) {
        for( Enemy e : playing.getEnemyManager().getEnemies()){
            if(e.getBounds().contains(p.getPos())){
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(Projectile p : projectiles)
            if(p.isActive()){
                g2d.translate(p.getPos().x,p.getPos().y);
                g2d.rotate(Math.toRadians(p.getRotation()));
                g2d.drawImage(projImgs[p.getProjectileType()],-16,- 16,null );
                g2d.rotate(-Math.toRadians(p.getRotation()));
                g2d.translate(-p.getPos().x,-p.getPos().y);

            }




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

    }

