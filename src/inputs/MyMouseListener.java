package inputs;

import main.Game;
import main.GameStates;

import javax.xml.bind.annotation.XmlType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * klasa MyMouseListener sluzy do trzymania wszystkich metod zwiazanych z MouseListener
 */
public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Game game;

    /**
     * konstruktor klasy MyMouseListener
     * @param game
     */
    public MyMouseListener(Game game) {
        this.game = game;
    }

    /**
     * metoda mouseClicked dzieki ktorej naciskam na przycisk
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1){

        switch (GameStates.gameState) {
            case PLAYING:
                game.getPlaying().mouseClicked(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mouseClicked(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseClicked(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseClicked(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mouseClicked(e.getX(),e.getY());
                break;
            default:
                break;
        }
    }
        }

    /**
     * metoda mousePressed dziki ktorej zmienia sie game state
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.gameState) {
            case PLAYING:
                game.getPlaying().mousePressed(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mousePressed(e.getX(),e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousePressed(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mousePressed(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mousePressed(e.getX(),e.getY());
                break;
            default:
                break;
        }

    }

    /** metoda mouseReleased sluzy do eventu puszczenia przycisku
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gameState) {
            case PLAYING:
                game.getPlaying().mouseReleased(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mouseReleased(e.getX(),e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseReleased(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseReleased(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mouseReleased(e.getX(),e.getY());
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /** metoda mouseDragged sluzy do eventu przeciagania myszki
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameStates.gameState) {
            case PLAYING:
                game.getPlaying().mouseDragged(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mouseDragged(e.getX(),e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseDragged(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseDragged(e.getX(), e.getY());
                break;
            default:
                break;
        }

    }

    /** metoda mouseMoved slyzaca do sluzy do eventu ruszenia myszki
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {

            switch (GameStates.gameState) {
                case PLAYING:
                    game.getPlaying().mouseMoved(e.getX(), e.getY());
                    break;
                case MENU:
                    game.getMenu().mouseMoved(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mouseMoved(e.getX(), e.getY());
                    break;
                case EDIT:
                    game.getEditor().mouseMoved(e.getX(), e.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mouseMoved(e.getX(),e.getY());
                    break;
                default:
                    break;
            }


    }
}
