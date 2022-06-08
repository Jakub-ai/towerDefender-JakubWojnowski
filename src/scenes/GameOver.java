package scenes;

import UI.MyButton;
import main.Game;

import java.awt.*;

import static main.GameStates.*;

/** klasa Game over jest to klasa w ktorej znajduja sie metody z sekwencje GameOver po skonczeniu gry
 *
 */
public class GameOver extends GameScene implements SceneMethods {
    private MyButton bMenu, bReplay;
    public GameOver(Game game) {
        super(game);
        initButtons();
    }

    /** inicjuje initButtons przyciski
     *
     */
    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 300;
        int yOffset = 100;

        bMenu = new MyButton("Menu", x,y,w,h);
        bReplay = new MyButton("Replay", x, y + yOffset, w,h);
    }

    /** metoda render renderuje przyciski
     *
     * @param g Graphics
     */
    @Override
    public void render(Graphics g) {
        //game over text
        g.setFont(new Font("LucidaSans",Font.BOLD,50));
        g.setColor(Color.RED);
        g.drawString("Game OVER !!!", 160,80);
        //buttons
        g.setFont(new Font("LucidaSans",Font.BOLD,20));
        bMenu.draw(g);
        bReplay.draw(g);

    }

    /** replayGame funkcjionalnosc przycisku replay poprostu dzieki tej metodzie gramy jeszcze raz
     *
     */
    private void replayGame() {
        resetAll();
        SetGameState(PLAYING);
    }
    private void resetAll(){
        game.getPlaying().resetEverything();
    }

    /** mouseclicked zdarzenie wcisniecia przycisku myszy
     * daje funkcjonalnosc zdarzeniu po wcisnieciu danego przycisku wracamy do menu lub gramy jeszcze raz
     * @param x
     * @param y
     */
    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
            resetAll();
        }else if (bReplay.getBounds().contains(x, y))
            replayGame();

    }

    /** metoda mousemoved sluzy do zdarzenia ruchu myszki i gdy jest nad przyciskiem daje nam feedback czyli efekt wizualny kiedy myszka jest nad przyciskiem
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMouseOver(true);

    }

    /** metoda mousepressed daje nam feedback wizualny dla zdarzenia przycisku myszy na przycisku
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMousePressed(true);

    }

    /** mousereleased resetuje booleany czyli efekty wizualne przycisku
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseReleased(int x, int y) {
        bReplay.resetBooleans();
        bMenu.resetBooleans();

    }

    /** metoda mouseDragged w zasadzie w tym trybie nic nie robi
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseDragged(int x, int y) {

    }
}
