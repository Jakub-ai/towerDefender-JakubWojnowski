package scenes;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods {
    private BufferedImage img;
    private ArrayList<BufferedImage> atlas = new ArrayList<>();
    private Random random;


    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        LoadAtlas();
    }

    @Override
    public void render(Graphics g) {
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                g.drawImage(atlas.get(getRandInt()),x*32, y*32, null );

            }

        }

    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/atlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadAtlas() {
        for (int h = 0;  h < 4; h++){
            for(int w = 0; w < 10; w++ ){
                atlas.add( img.getSubimage(w * 32, h * 32, 32, 32));

            }
        }
    }

    private int getRandInt(){
        return random.nextInt(30);
    }
}
