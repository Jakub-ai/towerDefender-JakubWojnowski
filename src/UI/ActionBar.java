package UI;

import java.awt.*;
import java.text.DecimalFormat;

import helpz.Constants;
import objects.Tower;
import scenes.GameOver;
import scenes.Playing;

import static main.GameStates.*;

public class ActionBar extends Bar{

    private Playing playing;
    private MyButton bMenu, bPause;
    private Tower selectedTower;
    private Tower displayedTower;
    private DecimalFormat formatter;
    private int gold = 150;
    private boolean showTowerCost;
    private int towerCostType;
    private MyButton sellTower, upgradeTower;

    private int lives = 5;

    private MyButton[] towerButtons;
    public ActionBar(int  x, int y, int width, int height, Playing playing ) {

        super(x,y,width,height);
       this.playing = playing;
       formatter = new DecimalFormat("0.0");

        initButtons();
   }
    public void resetEverything() {
        lives = 10;
        towerCostType = 0;
        showTowerCost = false;
        gold = 150;
        selectedTower = null;
        displayedTower = null;

    }
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bPause = new MyButton("Pause", 2, 682,100,30);
        towerButtons = new MyButton[4];
        int w=50;
        int h=50;
        int xStart=110;
        int yStart=650;
        int xOffset = (int) (w * 1.1f);

        for(int i = 0; i < towerButtons.length; i++ ){
            towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w,h, i);
        }

        sellTower = new MyButton("Sell", 420,702,80,25 );
        upgradeTower = new MyButton("Upgrade", 545,702,80,25 );


    }
    public void removeOneLive(){
        lives--;
        if(lives <= 0)
            SetGameState(GAME_OVER);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);
        for (MyButton b : towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(b.x,b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);

            drawButtonFeedback(g,b);
        }
        }


    public void draw(Graphics g){

        //background
       g.setColor(new Color(115,150,109));
       g.fillRect(x, y, width, height);

       //buttom
    drawButtons(g);

    //display tower
        drawDisplayedTower(g);
        //wave infos
        drawWaveInfo(g);
        
        
        //draw money
        drawGoldAmount(g);

        //draw tower cost
        if(showTowerCost = true)
        drawTowerCost(g);
        //Game Paused Text
        if(playing.isGamePaused()){
            g.setColor(Color.BLACK);
            g.drawString("Game is FROZEN !", 20, 790);
        }

        // draw lives
        g.setColor(Color.GREEN);
        g.drawString("Lives: " + lives, 110,750);
}

    private void drawTowerCost(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(205,705,140,80);
        g.setColor(Color.black);
        g.drawRect(205,705,140,80);

        g.drawString(" " + getTowerCostName(), 210,725);
        g.drawString("cost: " + getTowerCostValue() + "g", 210, 750);
        
        //shows if gold needed
        
        if(isTowerCostMoreThanCurrentGold()){
            g.setColor(Color.RED);
            g.drawString("Gold needed !", 210, 780);

        }

    }

    private boolean isTowerCostMoreThanCurrentGold() {
        return getTowerCostValue() > gold;
    }

    private int getTowerCostValue() {
        return Constants.Towers.GetTowerCost(towerCostType);
    }

    private String getTowerCostName() {
        return Constants.Towers.GetName(towerCostType);
    }

    private void drawGoldAmount(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawString("Gold: " + gold, 110,725);

    }

    private void drawWaveInfo(Graphics g) {
        g.setFont(new Font("LucidaSans",Font.BOLD,20));
        g.setColor(new Color(80,3,60));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);

    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1 )+ "/" + size, 425, 770 );
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountofAliveEnemies();
        g.drawString("Enemies Left: " + remaining, 425, 790);
        
    }

    private void drawWaveTimerInfo(Graphics g){
        if(playing.getWaveManager().isWaveTimerStarted()){


            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedText = formatter.format(timeLeft);
            g.drawString("Time Left: " + formattedText, 425,750 );


        }

    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower != null){
            g.setColor(Color.gray);
            g.fillRect(410,645,220,85);
            g.setColor(Color.black);
            g.drawRect(410,645,220,85);
            g.drawRect(420,645,220,85);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
            g.setFont(new Font("LucidaSans",Font.BOLD,15));
            g.drawString(""+ Constants.Towers.GetName(displayedTower.getTowerType()),480,660);
            g.drawString("ID: "+ displayedTower.getId(),480,675);
            g.drawString("Tier: "+ displayedTower.getTier(),560,660);


            drawDisplayedTowerBorder(g);

            drawDisplayedTowerRange(g);

            //sell button

            sellTower.draw(g);
            drawButtonFeedback(g,sellTower);


            // Upgrade button

            if(displayedTower.getTier() < 3 && gold >= getUpgradeAmount(displayedTower)){
                upgradeTower.draw(g);
                drawButtonFeedback(g,upgradeTower);
            }


            if(sellTower.isMouseOver()){
                g.setColor(Color.RED);
                g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 480, 695);

            }else if(upgradeTower.isMouseOver() && gold >= getUpgradeAmount(displayedTower)){
                g.setColor(Color.BLUE);
                g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g", 480, 695);
            }


        }

    }

    private int getUpgradeAmount(Tower displayedTower) {
        return (int)(Constants.Towers.GetTowerCost(displayedTower.getTowerType()) * 0.3f);
    }

    private int getSellAmount(Tower displayedTower) {
        int upgradeCost = (displayedTower.getTier() -1) * getUpgradeAmount(displayedTower);
        upgradeCost *= 0.5f;
        return Constants.Towers.GetTowerCost(displayedTower.getTowerType()) / 2 + upgradeCost;
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange()*2) / 2,displayedTower.getY() + 16 - (int)(displayedTower.getRange()*2) /2,(int)displayedTower.getRange()*2,(int)displayedTower.getRange()* 2);
    }

    //ta metoda zmienia kolor granic wiezy po jej wybraniu. dzieki czemu wiemy ktora wieze wybralismy z mapy
    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32,32);
    }

    // w ta metoda przechowuje wieze ktora wybralismy w sekwencji playing i jest przesylana do Actionbar dzieki czemu mozemy ją uzyc w innych metodach
    public void displayedTower(Tower t) {
        displayedTower = t;
    }

    private void sellTowerClicked() {

        playing.removeTower(displayedTower);
        gold += Constants.Towers.GetTowerCost(displayedTower.getTowerType()) / 2;
        int upgradeCost = (displayedTower.getTier() -1) * getUpgradeAmount(displayedTower);
        upgradeCost *= 0.5f;
        gold += upgradeCost;
        displayedTower = null;
    }
    private void togglePause() {
        playing.setGamePaused(!playing.isGamePaused());
        if(playing.isGamePaused())
            bPause.setText("UnPause");
        else
            bPause.setText("Pause");

    }
    private void upgradeTowerClicked() {
        playing.upgradeTower(displayedTower);
        gold -= getUpgradeAmount(displayedTower);
    }
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else if(bPause.getBounds().contains(x,y))
            togglePause();

        else{
            if(displayedTower != null){
                if(sellTower.getBounds().contains(x,y)){
                    sellTowerClicked();

                    return;
                }else if(upgradeTower.getBounds().contains(x,y) && displayedTower.getTier() < 3 && gold >= getUpgradeAmount(displayedTower)){
                    upgradeTowerClicked();

                    return;
                }

            }
            for (MyButton b : towerButtons){
                if(b.getBounds().contains(x,y)){

                    if(!isGoldEnoughForTower(b.getId()))
                        return;
                    selectedTower = new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }

            }
        }

    }




    private boolean isGoldEnoughForTower(int towerCostType) {
        return gold >= Constants.Towers.GetTowerCost(towerCostType);

    }


    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);
        showTowerCost = false;
        sellTower.setMouseOver(false);
        upgradeTower.setMouseOver(false);
        for (MyButton b : towerButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if(bPause.getBounds().contains(x,y)){
            bPause.setMouseOver(true);
        }
        else{
            if(displayedTower != null){
                if(sellTower.getBounds().contains(x,y)){
                    sellTower.setMouseOver(true);
                    return;
                }else if(upgradeTower.getBounds().contains(x,y) && displayedTower.getTier() < 3){
                    upgradeTower.setMouseOver(true);
                    return;
                }
            }
            for(MyButton b : towerButtons)
                if(b.getBounds().contains(x,y)) {
                    b.setMouseOver(true);
                    showTowerCost = true;
                    towerCostType = b.getId();
                    return;
                }
        }
    }


    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if(bPause.getBounds().contains(x,y)) {
            bPause.setMousePressed(true);
        }else{
            if(displayedTower != null){
                if(sellTower.getBounds().contains(x,y)){
                    sellTower.setMousePressed(true);
                    return;
                }else if(upgradeTower.getBounds().contains(x,y) && displayedTower.getTier() < 3){
                    upgradeTower.setMousePressed(true);
                    return;
                }

            }
            for(MyButton b : towerButtons)
                if(b.getBounds().contains(x,y)){
                    b.setMousePressed(true);
                    return;

        }

                }

    }


    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bPause.resetBooleans();
        for(MyButton b : towerButtons)
            b.resetBooleans();

        sellTower.resetBooleans();
        upgradeTower.resetBooleans();
    }


    public void payForTower(int towerType) {
        this.gold -= Constants.Towers.GetTowerCost(towerType);
    }

    public void addGold(int getReward) {
        this.gold += getReward;
    }

    public int getLives() {
        return lives;
    }


}
