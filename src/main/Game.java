package main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public class Game extends JFrame{
private GameScreen gameScreen;

private BufferedImage img;


    public Game(){

      importImg();

        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen(img);
        add(gameScreen);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("atlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println("start Game!!!");
        Game game = new Game();
    }
}
