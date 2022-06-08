package scenes;

import java.awt.*;

/** interface klasa wymusza na klasach impplementujace interface umieszczenie koordynatow x y
 *
 */
public interface SceneMethods {

    public void render(Graphics g);
    public void mouseClicked(int x, int y);
    public void mouseMoved(int x, int y);
    public void mousePressed(int x, int y);
    public void mouseReleased(int x, int y);
    public void mouseDragged(int x, int y);

}
