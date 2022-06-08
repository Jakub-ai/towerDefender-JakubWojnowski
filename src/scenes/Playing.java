package scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import UI.ActionBar;
import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;

import static helpz.Constants.Enemies.GHOST;
import static helpz.Constants.Tiles.GRASS_TILE;

/** klasa Playing, w niej znajduje sie cala mechanika trybu gry
 *
 */
public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;


    private ActionBar actionBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private PathPoint start,end;
    private WaveManager waveManager;
    private Tower selectedTower;
    private ProjectileManager projManager;
    private int goldTick;
    private boolean gamePaused;

    /** konstruktor klasy playing
     * inicjuje wszystkie managery zwiazane z trybem grania
     * @param game okno
     */
    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        actionBar = new ActionBar(0,640,640, 160, this);
        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
        projManager = new ProjectileManager(this);
        waveManager = new WaveManager(this);

    }

    /** metoda  loadDefaultLevelladuje mape poczatkowa lub zprojektowana przez gracza
     *
     */
    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    /** ustawia tablice 2 wymiarowa level
     *
     * @param lvl tablica
     */
    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    /** metoda update aktualizuje wszystko co sie dzieje podczas rozgrywki
     * glownie liczy czas ktory jest uzywany do wielu rzeczy takich jak dodawanie zlota graczowi wraz z uplywem czasu czy odliczanie przeciwkow
     * czy spawnuje kolejna fale przeciwnikow
     */
    public void update() {

        if (!gamePaused) {
            updateTick();
            waveManager.update();

            //Gold tick
            goldTick++;
            if (goldTick % (60 * 3) == 0)
                actionBar.addGold(2);
            if (isAllEnemiesDead()) {

                if (isThereMoreWaves()) {
                    waveManager.startWaveTimer();
                    //check timer
                    if (isWaveTimerOver()) {
                        waveManager.increaseWaveIndex();
                        enemyManager.getEnemies().clear();
                        waveManager.resetEnemyIndex();

                    }
                }

            }

            if (isTimeForNewEnemy()) {
                spawnEnemy();
            }
            enemyManager.update();
            towerManager.update();
            projManager.update();
        }
    }

    /** metoda isWaveTimerOver boolean sprawdza czy zegar pomiedzy falami sie skonczyl
     *
     * @return waveManager.isWaveTimerOver()
     */
    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimerOver();
    }

    /** metoda isThereMoreWaves sprawdza czy zostalo wiecej fal przeciwnikow
     *
     * @return waveManager.isThereMoreWaves
     */
    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    /** metoda isAllEnemiesDead sprawdza czy wszystkie obiekty tupu enemy przestaly byc aktywne
     *
     * @return zwraca false albo true
     */
    private boolean isAllEnemiesDead() {
        if(waveManager.isThereMoreEnemiesInWave()){
            return false;
        }
        for(Enemy e : enemyManager.getEnemies())
            if(e.isAlive())
                return false;
        return true;
    }

    /** setSelectedTower ustawia wybrana texture
     *
     * @param selectedTower  wubrana textura
     */
    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;

    }

    /** metoda spawnEnemy dodaje kolejnego przeciwnika w fali
     *
     */
    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }
    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()){
            if(waveManager.isThereMoreEnemiesInWave())
                return true;

        }
        return false;

    }

    /** metoda sluzy do renderowania obiektow w sekwencji playing
     *
     * @param g graphics
     */
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projManager.draw(g);
        drawSelectedTower(g);
        drawHighLight(g);
        
        drawWaveInfos(g);

    }

    private void drawWaveInfos(Graphics g) {

    }

    /** metoda daje feedback wizualny kiedy kursor jest nad przyciskiem
     *
     * @param g graphics
     */
    private void drawHighLight(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(mouseX,mouseY, 32,32);
    }

    /** metoda drawSelectedTower wyswietla wybrany obiekt tower
     *
     * @param g graphics
     */
    private void drawSelectedTower(Graphics g) {
        if(selectedTower != null) {
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
        }

    }

    /** metoda drawLevel wyswietla cala mape wraz z animacjami
     *
     * @param g graphics
     */
    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if(isAnimation(id)){
                    g.drawImage(getSprite(id,animationIndex), x * 32, y * 32, null);
                }else
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    /** metoda zwraca typ textury wraz z jej charakterystyka
     *
     * @param x koordynaty
     * @param y koordynaty
     * @return game.getTileManager().getTile(id).getTileType()
     */
    public int getTileType(int x, int y) {
        int xCord = x / 32;
        int yCord = y / 32;
        if (xCord < 0 || xCord > 19)
            return 0;
        if( yCord < 0 || yCord > 19)
            return 0;
        int id = lvl[y/32][x/32];
       return game.getTileManager().getTile(id).getTileType();

    }

    /** metoda mouseClicked sluzy do obslugi zdarzenia mouseclicked jezeli jest poza mapa przekierewuje nas do
     * actionbar. jesli chcemy ustawic nowa wieze sprawdza czy miejsce gdzie chcemy ja postawic jest na tecturze typu grass i czy nie jest tam juz inna wieza
     *
     * @param x
     * @param y
     */
    @Override
    public void mouseClicked(int x, int y) {

        if(y >= 640)
            actionBar.mouseClicked(x,y);
        else{
            if(selectedTower != null){
                if(isTleGrass(mouseX,mouseY)) {
                    if (getTowerAt(mouseX, mouseY) == null) {
                        towerManager.addTower(selectedTower, mouseX, mouseY);
                        removeGold(selectedTower.getTowerType());
                        
                        selectedTower = null;
                        
                        
                    }
                }
            }else{
                //get tower if exist
                Tower t = getTowerAt(mouseX,mouseY);
                    actionBar.displayedTower(t);
            }
        }


    }

    /** metoda removeGold zabiera odpowiednia ilosc zlota z konta gracza odpowiednio z kosztem wiezy
     *
     * @param towerType typ wiezy
     */
    private void removeGold(int towerType) {
        actionBar.payForTower(towerType);
    }

    /** metoda sluzy do updatowaniu wiezy
     *
     * @param displayedTower ustawiona wieza
     */
    public void upgradeTower(Tower displayedTower) {
        towerManager.upgradeTower(displayedTower);

    }

    /** metoda sluzy do usuniecia wiezy ktora jest postawiona na mapie
     *
     * @param displayedTower ustawiona wieza
     */
    public void removeTower( Tower displayedTower) {
        towerManager.removeTower(displayedTower);
    }

    /**
     * metoda getTowerAt zwraca nam koordynaty ustawionej wiezy metoda glownie sluzy do sprawdzenia czy w danym miejscu jest juz taki obiekt
     * @param x
     * @param y
     * @return
     */
    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x,y);
    }

    /** metoda isTleGrass sluzy do sprawdzania czy dana textura jest typem grass
     *
     * @param x koordynaty
     * @param y koordynaty
     * @return GRASS_TILE
     */
    private boolean isTleGrass(int x, int y) {
    int id = lvl[y/32][x/32];
    int tileType = game.getTileManager().getTile(id).getTileType();
    return tileType == GRASS_TILE;

    }

    /** metoda shootEnemy sluzy do spawnowania nowych pociskow podczas wymiany ognia
     *
     * @param t tower
     * @param e enemy
     */
    public void shootEnemy(Tower t, Enemy e) {
        projManager.newProjectile(t,e);
    }

    /** metoda setGamePaused zatrzymuje gre
     *
     * @param gamePaused tryb freeze
     */
    public void setGamePaused(boolean gamePaused){
        this.gamePaused =gamePaused;
    }

    /** metoda keyPressed obsluguje key event dla przycisku excape
     * dzieki tej metodzie w momencie wybrania tower po wcisnieciu przycisku juz nie mamy wybranego obiektu
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            selectedTower = null;
        }
    }

    /** metoda mouseMoved sluzy do obslugi koordynatow gdzie w danym momencie jest myszka na oknie
     * jesli przejdziemy myszka z mapy do actionBar zaczynamy korzystac z metod w actionbar
     * @param x
     * @param y
     */
    @Override
    public void mouseMoved(int x, int y) {
    if(y >= 640)
        actionBar.mouseMoved(x,y);
    else{
        mouseX = (x / 32) * 32;
        mouseY = (y / 32) * 32;
    }

    }

    /** metoda mousePressed daje nam wizualny feedback mousepressed
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mousePressed(int x, int y) {
        if(y >= 640){
            actionBar.mousePressed(x,y);
        }
    }

    /** metoda mouseReleased resetuje boolean kdla feedback efektu wizualnego
     *
     * @param x koordynat
     * @param y koordynat
     */
    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x,y);
    }

    @Override
    public void mouseDragged(int x, int y) {


    }

    /** metoda rewardPlayer dodaje zloto do licznika w actionBar za pokonanie przeciwnika
     *
     * @param enemyType typ wroga
     */
    public void rewardPlayer(int enemyType){
        actionBar.addGold(Constants.Enemies.GetReward(enemyType));
    }

    /** metoda getTowerManager zwraca towerManager
     *
     * @return towerManager
     */
    public TowerManager getTowerManager() {
        return towerManager;
    }

    /** metoda getEnemyManager zwraca enemymanager
     *
     * @return enemyManager
     */
    public EnemyManager getEnemyManager() {
        return enemyManager;
    }


    /** metoda getWaveManager zwraca waveManager
     *
     * @return waveManager
     */
    public WaveManager getWaveManager() {
        return waveManager;
    }

    /** metoda isGamePaused boolean sprawdza czy gra jest zatrzymana
     *
     * @return gamePaused
     */
    public boolean isGamePaused() {
        return gamePaused;
    }

    /** metoda removeOneLive odbiera jedno zycie kiedy przeciwnik dojdzie do pathpoint end
     *
     */
    public void removeOneLive() {
        actionBar.removeOneLive();
    }
/** metoda resetEverything jak wskazuje nazwa resetuje cala sekwencje
 *
 */
    public void resetEverything() {
        actionBar.resetEverything();
        //reset managers
        enemyManager.reset();
        towerManager.reset();
        projManager.reset();
        waveManager.reset();
        mouseX = 0;
        mouseY = 0;
        selectedTower = null;
        goldTick = 0;
        gamePaused = false;

    }
}


