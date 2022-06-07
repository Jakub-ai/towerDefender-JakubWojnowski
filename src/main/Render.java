package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/** klasa render pozwala renderowac gameStates
 *
 */
public class Render {


    private Game game;
    public Render(Game game){
        this.game = game;


    }

    /** metoda render pozwala renderowac gameState na inny gameState np playing
     *
     * @param g
     * @return nic nie zwraca
     */
    public void render(Graphics g){

        switch(GameStates.gameState){
            case MENU:
                game.getMenu().render(g);
                break;

            case PLAYING:
                game.getPlaying().render(g);
                break;

            case SETTINGS:
                game.getSettings().render(g);
                break;

            case EDIT:
                game.getEditor().render(g);
                break;
            case GAME_OVER:
                game.getGameOver().render(g);
                break;

        }

    }

}
