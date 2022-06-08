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

/** klasa Menu zajmuje sie charakterystyka main Menu
 *
 */
public class Menu extends GameScene implements SceneMethods {

    private MyButton bPlaying, bEdit, bSettings, bQuit;
    public Menu(Game game) {
        super(game);

        initButtons();
    }

    /** metoda initButtons inicjuje przyciski
     *
     */
    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Edit", x, y + yOffset, w,h);
        bSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
    }

    /** metoda render renderuje przyciski
     *
     * @param g graphics
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,640,640);
   drawButtons(g);

    }

    /** metoda mouseClicked obsluguje zdarzenie nacisniecie przycisku myszy na przycisku
     * zmieniamy sekwencje z menu na PLAYING, EDIT lub SETTINGS
     * @param x
     * @param y
     */
    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            SetGameState(PLAYING);
        } else if (bEdit.getBounds().contains(x, y)) {
            SetGameState(EDIT);
        }else if (bSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y))
            System.exit(0);

    }

    /** metoda mouseMoved obsluguje zdarzenie mouseover czyli feedback wizualny ze myszka jest nad przyciskiem
     *
     * @param x koordynaty
     * @param y koordyanty
     */
    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    /** metoda mousepressed daje nam feedback wizualny po nacisnieciu przycisku
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMousePressed(true);
        }else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    /** metoda mouseReleased restuje efekty wizualne przycisku
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();

    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    /** metoda resetButtons resetuje przyciski
     *
     */
    private void resetButtons() {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    /** metoda drawButtons wyswietla nam przyciski
     *
     * @param g graphics
     */
    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }



 /*   private int getRandInt(){
        return random.nextInt(30);
    }*/
}
