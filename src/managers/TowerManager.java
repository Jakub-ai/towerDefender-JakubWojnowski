package managers;

import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helpz.Constants.Towers.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private Tower tower;

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImgs();
        initTowers();
    }

    private void initTowers() {
        tower = new Tower(5*32,14*32, 0,BAZOOKA);

    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[4];
        towerImgs[3] = atlas.getSubimage(5 * 32, 2*32, 32,32);
        for(int i = 0; i < 3; i++)
            towerImgs[i] = atlas.getSubimage((5 + i) * 32, 32,32,32);
    }
    public void update(){

    }
    public void draw(Graphics g){
        g.drawImage(towerImgs[BAZOOKA],tower.getX(), tower.getY(), null);

    }



}
