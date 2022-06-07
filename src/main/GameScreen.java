package main;
import inputs.KeyboardListener;
import inputs.MyMouseListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/** klasa GameScreen klasa okresla charakterystyke okno programu
 *
 */
public class GameScreen extends JPanel {

    private Game game;
    private Dimension size;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    /** konstruktor klasy GameScreen
     *
     * @param game
     */
    public GameScreen(Game game){
        this.game = game;


        SetPanelSize();

    }

    /** metoda initInputs inicjuje inputy
     * @return nic nie zwraca
     */
    public void initInputs(){
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    /** SetPanelSize ustala wielkosc okna
     * @return nic nie zwraca
     */
    private void SetPanelSize() {
        size = new Dimension(640, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    /** metoda paintComponent sluzy do renderowania
     *
     * @param g
     * @return nic nie zwraca
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);

    }

}
