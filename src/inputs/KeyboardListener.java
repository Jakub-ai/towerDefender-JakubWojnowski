package inputs;

import main.Game;
import main.GameStates;
import scenes.Menu;

import static main.GameStates.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** KeyboardListener jest to klasa w ktorej sa wszystkie metody zwiazane z przyciskami na klawiaturze
 *
 */
public class KeyboardListener implements KeyListener {
    private Game game;

    /**
     * konstruktor klasy KeyboardListener
     * @param game
     */
    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /** keyPressed rozdziela w jakiej sekwencja jest uzyta metoda
     * metoda
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(GameStates.gameState == EDIT)
            game.getEditor().keyPressed(e);
        else if(GameStates.gameState == PLAYING)
            game.getPlaying().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
