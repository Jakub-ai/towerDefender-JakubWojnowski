package scenes;
import main.Game;

import java.awt.image.BufferedImage;

/** klasa gamescene zajmuje sie ogolna obsluga okna
 *
 */
public class GameScene {

    protected Game game;
    protected int animationIndex;
    protected int ANIMATION_SPEED = 25;
    protected int tick;

    /** konstruktor klasy gameScene
     *
     * @param game okno
     */
    public GameScene(Game game){this.game = game ;}

    /** metoda get game zwraca game
     *
     * @return game
     */
    public Game getGame(){
        return game;
    }

    /** metoda isAnimation zwraca wartosc boolean dla animacji dla danej textury
     *
     * @param spriteID
     * @return true or false
     */
    protected boolean isAnimation(int spriteID) {
        return game.getTileManager().isSpriteAnimation(spriteID);
    }

    /** metoda updateTick aktualizuje czas
     *
     */
    protected void updateTick() {
        tick ++;
        if(tick >= ANIMATION_SPEED){
            tick = 0;
            animationIndex++;
            if(animationIndex >= 4){
                animationIndex = 0;
            }
        }
    }

    /** metoda getSprite zwraca id textury
     *
     * @param spriteID
     * @return spriteID
     */
    protected BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }

    /** metoda getSprite zwraca spriteID oraz index animacji
     *
     * @param spriteID
     * @param animationIndex
     * @return spriteID,animationIndex
     */
    protected BufferedImage getSprite(int spriteID, int animationIndex) {
        return game.getTileManager().getAniSprite(spriteID,animationIndex);
    }
}
