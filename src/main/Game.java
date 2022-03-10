package main;
import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.awt.image.BufferedImage;

public class Game extends JFrame implements Runnable{
private GameScreen gameScreen;
private BufferedImage img;
private final double FPS_SET = 120.0;
private final double UPS_SET = 60.0;

private MyMouseListener myMouseListener;
private KeyboardListener keyboardListener;



private Thread gameThread;

    public Game(){


      importImg();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen(img);

        add(gameScreen);
        pack();
        setVisible(true);
    }
    private void initInputs(){
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("atlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
