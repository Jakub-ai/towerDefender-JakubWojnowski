package main;
import javax.swing.*;

public class Game extends JFrame{
private GameScreen gameScreen;
    public Game(){
        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen();
        add(gameScreen);
    }
    public static void main(String[] args){
        System.out.println("start Game!!!");
        Game game = new Game();
    }
}
