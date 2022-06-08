package UI;

import scenes.Playing;

import java.awt.*;

/** klasa Bar ustala charakterystyke obiektu Bar ktorym jest np. actionBar
 *
 */
public class Bar {
    protected int  x, y, width, height;

    /** konstruktor klasy Bar
     *
     * @param x koordynat
     * @param y koordynat
     * @param width wysokosc
     * @param height szerokosc
     */
    public Bar(int  x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //initButtons();
    }

    /** metoda drawButtonFeedback okresla nam jak dziala efekt wizualny akcji zwiazanej z przyciskiem
     *
     * @param g Graphics
     * @param b MyButton to kalsa w ktorej okreslona jest charakterystyka przycisku
     */
    protected void drawButtonFeedback(Graphics g, MyButton b){
        //Mouseover
        if(b.isMouseOver())
            g.setColor(new Color(86,66,255));
        else
            g.setColor((Color.BLACK));

        //Border
        g.drawRect(b.x, b.y, b.width,b.height);

        //MousePressed
        if(b.isMousePressed()) {
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
        }

    }
}
