package objects;

import helpz.Constants;

import static helpz.Constants.Towers.*;

public class Tower {
    private int x, y, id, towerType,damage, cdTick;
    private float  range, coolDown;
    private int tier;
    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        tier = 1;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCoolDown();

    }
    public void update(){
        cdTick++;
    }
    public void upgradeTower(){
        this.tier++;
        switch (towerType){
            case CANNON:
                damage += 5;
                range += 15;
                coolDown -= 5;
            case ARCHER:
                damage += 3;
                range += 10;
                coolDown -= 5;
                break;
            case MAGE:
                range += 10;
                coolDown -= 5;
                break;
            case BAZOOKA:
                damage += 5;
                range += 10;
                coolDown -= 2;
                break;
        }

    }
    public boolean isCoolDownOver() {
        return cdTick >= coolDown;
    }

    public void resetCoolDown() {
        cdTick = 0;
    }

    private void setDefaultRange() {
        range = Constants.Towers.GetDefaultRange(towerType);


    }

    private void setDefaultDmg() {
        damage = Constants.Towers.GetStartDamage(towerType);


    }
    private void setDefaultCoolDown() {
       coolDown = Constants.Towers.GetDefaultCoolDown(towerType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTowerType() {
        return towerType;
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public int getTier(){
        return tier;
    }


}

