package UI;

import java.awt.*;

/** klasa MyButton to miejsce w ktorym jest okreslona charakterystyka przyciskow dla calej gry
 *
 */
public class MyButton {

    public int x, y, width, height, id;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    /** konstruktor klasy MyButton
     *
     * @param text jest to string na przycisku
     * @param x koordynat
     * @param y koordynat
     * @param width szerokosc
     * @param height wysokosc
     */
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id= -1;
        initBounds();
    }

    /** jest to drugi konstruktor klasy MyButton ktory sluzy do przyciskow zwiazanych z texturami gry. glownie w trybie edycji
     *
     * @param text  jest to string na przycisku
     * @param x koordynat
     * @param y koordynat
     * @param width szerokosc
     * @param height wysokosc
     * @param id id przycisku
     */
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        initBounds();
    }

    /** metoda inicjuje granice obiektu a dokladnie jest to prostokat
     *
     */
    private void initBounds(){
        this.bounds = new Rectangle(x, y, width, height);
    }

    /** metoda draw sluzy do wyswietlania czesci obiektu w tym przypadku granica, cialo oraz text
     *
     * @param g Graphics
     */
    public void draw(Graphics g){
        //Body
        drawBody(g);

        //Border
        drawBorder(g);

        //Text
        drawText(g);

    }

    /** metoda okrewslajaca charakterystyke granicy obieku
     * kolor, wysokosc, szerokosc
     * @param g Graphics
     */
    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
      if(mouseOver){
            g.setColor(new Color(86,66,255));
            g.drawRect(x + 1, y + 1, width - 2, height - 2 );
            g.drawRect(x + 2, y + 2, width - 4, height - 4 );
        }

    }

    /** metoda drawBody sluzy do okreslenia kolory ciala oraz jego zmiane jezeli przycisk zostanie poddany feedbackowi
     *
     * @param g Graphics
     */
    private void drawBody(Graphics g) {

        g.setColor(new Color(86,66,54));

      /*  if(mouseOver) {
            g.setColor(Color.GRAY);
        }*/
         if(mousePressed){
            g.setColor(Color.GRAY);
        }
        g.fillRect(x, y, width, height);

    }

    /** metoda resetBooleans resteuje wszystkie efekty wizualne przycisku
     *
     */
    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    /** metoda setText okresla ze text to string
     *
     * @param text jest to string
     */
    public void setText(String text){
        this.text = text;
    }

    /** metoda setMousePressed zwraca wartosc boolean dla zdarzenia
     *
     * @param mousePressed zwraca status przycisku false or true
     */
    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }
    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;

    }

    /** zwraca wartosc boolean dla zdarzenia isMouseOver
     *
     * @return mouseOver
     */
    public boolean isMouseOver(){
        return mouseOver;
    }

    /** zwraca wartosc boolean dla zdarzenia isMousePressed
     *
     * @return mousePressed
     */
    public boolean isMousePressed(){
        return mousePressed;
    }

    /** metoda drawText okresla charakterystyke textu prcisku
     *
     * @param g Graphics
     */
    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
    }

    /** metoda getBounds zwraca rame prostokata
     *
     * @return bounds
     */
    public Rectangle getBounds(){
        return bounds;
    }

    /** metoda getId zwraca id przycisku
     *
     * @return id
     */
    public int getId(){
        return id;
    }

}
