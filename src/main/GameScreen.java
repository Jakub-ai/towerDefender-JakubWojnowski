package main;
import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;
public class GameScreen extends JPanel {
    private Random random;
    public GameScreen(){
    random = new Random();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);



        for(int y = 0; y < 20; y++){
       for(int x = 0; x < 20; x++){
          g.setColor (getRndColor());
           g.fillRect(x * 32, y * 32, 32,32);
       }

        }

    }
    private Color getRndColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }

}
