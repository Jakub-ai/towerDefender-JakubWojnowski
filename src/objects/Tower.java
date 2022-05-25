package objects;

import helpz.Constants;

public class Tower {
    private int x, y, id, towerType,damage, cdTick;
    private float  range, coolDown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCoolDown();

    }
    public void update(){
        cdTick++;
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


}

