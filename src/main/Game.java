package main;
import javax.swing.JFrame;

import helpz.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import managers.TileManager;
import scenes.*;

/** klasa Game jest to klasa Main dla calej gry
 *
 */
public class Game extends JFrame implements Runnable{
private GameScreen gameScreen;
private Thread gameThread;

private final double FPS_SET = 120.0;
private final double UPS_SET = 60.0;


//Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private GameOver gameOver;

    private TileManager tileManager;

    /** konstruktor klasy game ktory inicjuje wszystkie waazne funkcje dla gry
     *
     */
    public Game(){
        initClasses();
        createDefaultLevel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);



        add(gameScreen);
        pack();
        setVisible(true);
    }

    /** metoda createDefaultLevel tworzy standarwowa mape
     * @return nic nie zwraca
     */
    private void createDefaultLevel() {

        int [] arr = new int[400];
        for(int i = 0; i < arr.length; i++)
            arr[i] = 0;
        LoadSave.CreateLevel("new_level", arr);
    }

    /** metoda initClasses inicjuje klasy
     * @return nic nie zwraca
     */
    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        editing = new Editing(this);
        gameOver = new GameOver(this);


    }

    /** metoda start uruchamiajaca thready dla gry
     *
     */
    private void start(){
        gameThread = new Thread(this) {};
       gameThread.start();
    }

    /**
     * metoda updateGame init update dla gamestates
     * @return nic nie zwraca
     */
    private void updateGame() {

       switch(GameStates.gameState){

           case PLAYING:
               playing.update();
               break;
           case MENU:
               break;
           case SETTINGS:
               break;
           case EDIT:
               editing.update();
               break;
       }
    }

    /** metoda main uruchamiajaca gre
     *
     * @param args
     * @return nic nie zwraca
     */
    public static void main(String[] args){
        System.out.println("start Game!!!");
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();

    }

    /** metoda run stabilizuje liczbe FPS oraz UPS
     * @retun nic nie zwraca
     */
    @Override
    public void run() {

         double timePerFrame = 1000000000.0 / FPS_SET;
         double timePerUpdate = 1000000000.0 / UPS_SET;

         long lastFrame = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        long lastUpdate = System.nanoTime();
        long now;

        int frames = 0;
        int updates= 0;

        while(true) {

            now = System.nanoTime();
            //Render
            if (now - lastFrame >= timePerFrame) {

                repaint();
                lastFrame = now;
                frames++;


            }
            //Update
            if(System.nanoTime()- lastUpdate >= timePerUpdate){
                updateGame();
                lastUpdate = now;
                updates++;

            }
            //check Updates & Frames
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
            System.out.println("FPS: " + frames +" | UPS: " + updates);
            frames = 0;
            updates = 0;
            lastTimeCheck = System.currentTimeMillis();
            }

        }


    }

    /** metoda getRender zwraca render
     *
     * @return render
     */
    //getters and setters
    public Render getRender(){
        return render;
    }

    /** metoda getMenu zwraca menu
     *
     * @return menu
     */
    public Menu getMenu() {
        return menu;
    }

    /** metoda getPlaying zwraca playing
     *
     * @return playing
     */

    public Playing getPlaying() {
        return playing;
    }


    /** metoda getSettings zwraca settings
     *
     * @return settings
     */
    public Settings getSettings() {
        return settings;
    }

    /** metoda getEditor zwraca editing
     *
     * @return editing
     */
    public Editing getEditor(){
        return editing;
    }

    /** metoda getTileManager zwraca tileManager
     *
     * @return tileManager
     */
    public TileManager getTileManager(){
        return tileManager;
    }

    /** metoda getGameOver zwraca gameOver
     *
     * @return gameOver
     */
    public GameOver getGameOver() {
        return gameOver;
    }
}
