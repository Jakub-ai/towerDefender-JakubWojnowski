package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import UI.MyButton;

import static main.GameStates.*;

/** klasa settings jest to klasa obslugujaca sekwencje settings
 *
 */
public class Settings extends GameScene implements SceneMethods{

    private MyButton bMenu;

    /** konstruktor klasy Settings
     *
     * @param game okno
     */
    public Settings(Game game) {

        super(game);
        initButtons();
    }

    /** initButtons inicjuje przyciski
     *
     */
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 2, 100, 30);
    }

    /** render renderuje przyciski
     *
     * @param g graphics
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,640,640);

        drawButtons(g);
    }

    /** drawButtons wyswietla przycisk
     *
     * @param g graphics
     */
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    /** mouseClicked obsluguje event przycisku myszy
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);

    }

    /** obsluguje zdarzenie mousemoved czyli efekty wizualne
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);

    }

    /** mousePressed obsluguje zdearzenie na przycisku mousePressed czyli efekty wizualne
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);

    }

    /** mouseReleased restuje efekty wizualne
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

    /** resetuje wszystko co zwiazane z przyciskiem
     *
     */
    private void resetButtons() {
        bMenu.resetBooleans();
    }
}
