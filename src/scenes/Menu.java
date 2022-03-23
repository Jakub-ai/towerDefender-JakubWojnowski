package scenes;

import UI.MyButton;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import static main.GameStates.*;


public class Menu extends GameScene implements SceneMethods {
    private BufferedImage img;
    private ArrayList<BufferedImage> atlas = new ArrayList<>();
    private Random random;

    private MyButton bPlaying, bSettings, bQuit;
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        LoadAtlas();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bPlaying = new MyButton("Play", x, y, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,640,640);
   drawButtons(g);

    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            SetGameState(PLAYING);
        } else if (bSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y))
            System.exit(0);

    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();

    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
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
