package managers;

import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Towers.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int TowerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImgs();

    }


    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[4];
        towerImgs[3] = atlas.getSubimage(5 * 32, 2*32, 32,32);
        for(int i = 0; i < 3; i++)
            towerImgs[i] = atlas.getSubimage((5 + i) * 32, 32,32,32);
    }
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos,yPos,TowerAmount++,selectedTower.getTowerType()));


    }
    public void update(){

    }
    public void draw(Graphics g){
       // g.drawImage(towerImgs[BAZOOKA],tower.getX(), tower.getY(), null);
        for (Tower t : towers)
            g.drawImage(towerImgs[t.getTowerType()],t.getX(),t.getY(),null );

    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public Tower getTowerAt(int x, int y){
        for(Tower t : towers)
            if(t.getX() == x)
                if(t.getY() == y)
                    return t;
        return null;
    }

}
