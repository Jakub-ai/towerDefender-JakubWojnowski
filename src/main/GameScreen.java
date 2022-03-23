package main;
import inputs.KeyboardListener;
import inputs.MyMouseListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
public class GameScreen extends JPanel {

    private Game game;
    private Dimension size;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game){
        this.game = game;


        SetPanelSize();

    }

    public void initInputs(){
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }
    private void SetPanelSize() {
        size = new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);

    }

}
