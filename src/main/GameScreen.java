package main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
public class GameScreen extends JPanel {
    private Random random;
    private BufferedImage img;
    private ArrayList<BufferedImage> atlas = new ArrayList<>();
    public GameScreen(BufferedImage img){
        this.img = img;
        LoadAtlas();
    random = new Random();

    }

    private void LoadAtlas() {
        for (int h = 0;  h < 3; h++){
            for(int w = 0; w < 10; w++ ){
                atlas.add( img.getSubimage(w * 32, h * 32, 32, 32));

            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

       // g.drawImage(atlas.get(27),0,0,null);
   /* BufferedImage z = img.getSubimage(32*0,32,32,32);
    g.drawImage(z,0,0,null);*/


      for(int y = 0; y < 20; y++){
       for(int x = 0; x < 20; x++){
           g.drawImage(atlas.get(getRandInt()),x*32, y*32, null );

       }

        }

    }
    private int getRandInt(){
        return random.nextInt(30);
    }
    private Color getRndColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }

}
