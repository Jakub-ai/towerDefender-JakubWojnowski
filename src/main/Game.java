package main;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.awt.image.BufferedImage;

public class Game extends JFrame implements Runnable{
private GameScreen gameScreen;
private Thread gameThread;

private final double FPS_SET = 120.0;
private final double UPS_SET = 60.0;

private MyMouseListener myMouseListener;
private KeyboardListener keyboardListener;
//Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;

    public Game(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initClasses();


        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);

    }

    private void initInputs(){
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }


    private void start(){
        gameThread = new Thread(this) {};
       gameThread.start();
    }


    private void updateGame() {

        //System.out.println("Game Updated");
    }

    public static void main(String[] args){
        System.out.println("start Game!!!");
        Game game = new Game();
        game.initInputs();
        game.start();

    }

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
    //getters and setters
    public Render getRender(){
        return render;
    }

    public Menu getMenu() {
        return menu;
    }



    public Playing getPlaying() {
        return playing;
    }



    public Settings getSettings() {
        return settings;
    }


}
