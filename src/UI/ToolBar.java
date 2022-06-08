package UI;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helpz.LoadSave;
import objects.Tile;
import scenes.Editing;

/** klasa toolBar sluzy do panelu ktory jest pod mapa w trybie edycji
 *
 */
public class ToolBar extends Bar {
    private  Editing editing;
    private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton,ArrayList<Tile>>();
    private MyButton bMenu, bSave, bGrass, bWater, bRoadS, bRoadC, bIsland, bBeaches, bWaterC;
    private MyButton bPathStart, bPathEnd;
    private BufferedImage pathStart, pathEnd;
    private Tile selectedTile;
    private MyButton currentButton;


    private int currentIndex = 0;


    /** konstruktor klasy ToolBar
     *
     * @param x koordynat
     * @param y koordynat
     * @param width szerokosc
     * @param height wysokosc
     * @param editing tryb editing
     */
    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initPathImg();
        initButtons();
    }

    /** metoda initPathImg opdowiedzialna do inicjowania textur path img dokladnie 2 punktow start i end
     *
     */
    private void initPathImg() {
        pathStart = LoadSave.getSpriteAtlas().getSubimage(2 * 32, 3*32,32,32);
        pathEnd = LoadSave.getSpriteAtlas().getSubimage(3*32, 3*32,32,32);

    }

    /** metoda initButtons inicjuje przyciski
     *
     */
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bSave = new MyButton("Save", 2, 674, 100, 30);

        int w=50;
        int h=50;
        int xStart=110;
        int yStart=650;
        int xOffset = (int) (w * 1.1f);
        int i = 0;

        bGrass = new MyButton("Grass", xStart, yStart,w,h,i++);
        bWater = new MyButton("Water", xStart + xOffset, yStart, w,h,i++);
        initMapButtons(bWaterC,editing.getGame().getTileManager().getCorners(),xStart, yStart,xOffset,w,h,i++);
       initMapButtons(bRoadS,editing.getGame().getTileManager().getRoadsS(),xStart, yStart,xOffset,w,h,i++);
       initMapButtons(bRoadC,editing.getGame().getTileManager().getRoadsC(),xStart, yStart,xOffset,w,h,i++);
       initMapButtons(bBeaches,editing.getGame().getTileManager().getBeaches(),xStart, yStart,xOffset,w,h,i++);
       initMapButtons(bIsland,editing.getGame().getTileManager().getIslands(),xStart, yStart,xOffset,w,h,i++);

       bPathStart = new MyButton("PathStart", xStart, yStart + xOffset, w, h, i++);
       bPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, w, h, i++);






    }

    /** metoda initMapButtons inicjuje przyciski zwiazane z texturami
     *
     * @param b Mybutton
     * @param list ArrayList
     * @param x koordynat
     * @param y koordynat
     * @param xoff offset
     * @param w szerokosc
     * @param h wysokosc
     * @param id id przycisku
     */
    private void  initMapButtons(MyButton b, ArrayList<Tile>list,int x, int y , int xoff, int w, int h, int id){
        b = new MyButton("", x + xoff * id, y , w, h , id);
        map.put(b, list);

       // map.get(b).

    }

    /** zapisuje level edycji
     *
     */
    private void saveLevel(){
        editing.saveLevel();

    }

    /** metoda rotuje wybrany przycisk ktory jest textura. polega to na tym ze kazdy przycisk reprezentuje rodzaj textury
     * trawa, droga, plaza, zakret drogi etc. kiedy wybieram dana texture za pomoca przycisku rotuje sb dana texture prawo lewo gora dol
     *
     */
    public void rotateSprite() {
        if (map.get(currentButton) != null) {
            currentIndex++;
            if (currentIndex >= map.get(currentButton).size()) {
                currentIndex = 0;
            }
            selectedTile = map.get(currentButton).get(currentIndex);
            editing.setSelectedTile(selectedTile);
        }
    }

    /** metoda draw odpowiedzialna jest za kolor oraz wyswietla przyciski
     *
     * @param g Graphics
     */
    public void draw(Graphics g){

        //background
        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);

        //button
        drawButtons(g);
    }

    /** metopda drawButtons jest odpowiedzialna za wyswietlanie przyciskow powiazane z texturami sluzace do edycji
     *
     * @param g Graphics
     */
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
//        bPathStart.draw(g);
//        bPathEnd.draw(g);
        drawPathButton(g,bPathStart, pathStart);
        drawPathButton(g,bPathEnd, pathEnd);

        // drawTileButtons(g);
        drawNormalButton(g,bGrass);
        drawNormalButton(g,bWater);
        drawSelectedTile(g);
        drawMapButtons(g);

    }

    /** metoda drawPathButton sluzy do wyswietlania przycisku powiazanego z punktami startu i konca
     *
     * @param g Graphics
     * @param b MyButton
     * @param img BufferedImage
     */
    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.x, b.y, b.width, b.height, null);
        drawButtonFeedback(g, b);
    }

    /** metoda drawNormalButton sluzy do wyswietlania przycisku tzw. zwyklego czyli na przyklad MENU
     *
     * @param g Graphics
     * @param b MyButton
     */
    private void drawNormalButton(Graphics g, MyButton b) {
        g.drawImage(getButtImg(b.getId()),b.x,b.y,b.width,b.height,null);
        drawButtonFeedback(g,b);


    }

    /** metoda drawMapButtons wyswietla przyciski zwiazne z texturami
     *
     * @param g Graphics
     */
    private void drawMapButtons(Graphics g) {
        for(Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()){
            MyButton b  = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, b.x,b.y,b.width,b.height,null);
            drawButtonFeedback(g,b);


        }

    }

    /** metoda drawSelectedTile sluzy do edycji mapy za pomoca textury wybrane po przez wcisniecie przycisku
     *
     * @param g Graphics
     */
    private void drawSelectedTile(Graphics g) {

        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(),550, 650, 50, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(550,650,50, 50);
        }
    }

    /** metoda getButtImg zwraca id textury
     *
     * @param id textury czyli sprite
     * @return editing.getGame().getTileManager().getSprite(id)
     */
    public BufferedImage getButtImg(int id){
        return editing.getGame().getTileManager().getSprite(id);
    }

    /** metoda mouseClicked ktora sluzy do obslugi zdarzenia mouseClicked
     * sluzacego miedzy innymi do wyboru textur ktore reprezentuja przyciski
     * lub do zapisania mapy lub do wyjscia z sekwencji editting do menu
     * @param x
     * @param y
     */
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else if (bSave.getBounds().contains(x, y))
            saveLevel();
        else if (bGrass.getBounds().contains(x,y)){
            selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
            editing.setSelectedTile(selectedTile);
            return;
        }
        else if (bWater.getBounds().contains(x,y)){
            selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
            editing.setSelectedTile(selectedTile);
            return;
        }
        else if(bPathStart.getBounds().contains(x,y)){
            selectedTile = new Tile(pathStart,-1,-1);
            editing.setSelectedTile(selectedTile);

        }
        else if(bPathEnd.getBounds().contains(x,y)){
            selectedTile = new Tile(pathEnd,-2,-2);
            editing.setSelectedTile(selectedTile);

        }else {
            for (MyButton b : map.keySet())
                if (b.getBounds().contains(x, y)) {
                    selectedTile = map.get(b).get(0);
                    editing.setSelectedTile(selectedTile);
                    currentButton = b;
                    currentIndex = 0;
                    return;
                }
            }
        }

    /** metoda mouseMoved obsluguje zdarzenie moouseover. w momencie kiedy kursor jest nad ktorys z przyciskow jest to reprezentowane wizualnie w sposob graficzny
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bWater.setMouseOver(false);
        bGrass.setMouseOver(false);
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);
        for (MyButton b : map.keySet())
            b.setMouseOver(false);


        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if(bSave.getBounds().contains(x, y))
            bSave.setMouseOver(true);
        else if(bWater.getBounds().contains(x, y))
            bWater.setMouseOver(true);
        else if(bGrass.getBounds().contains(x, y))
            bGrass.setMouseOver(true);
        else if(bPathEnd.getBounds().contains(x,y))
            bPathEnd.setMouseOver(true);
        else if(bPathStart.getBounds().contains(x,y))
            bPathStart.setMouseOver(true);

        else{
            for(MyButton b : map.keySet()){
                if(b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }

    }

    /** metoda mousePressed obsluguje zdarzenie mousepressed
     * w momencie kiedy kursor jest nad przyciskiem i zostanie nacisniety przycisk myszy zostaje nam to zaprezentowane w sposob graficzny
     * @param x koordynaty
     * @param y koordynaty
     */
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if(bSave.getBounds().contains(x, y))
            bSave.setMousePressed(true);
        else if(bWater.getBounds().contains(x, y))
            bWater.setMousePressed(true);
        else if(bGrass.getBounds().contains(x, y))
            bGrass.setMousePressed(true);
        else if(bPathStart.getBounds().contains(x,y))
            bPathStart.setMousePressed(true);
        else if(bPathEnd.getBounds().contains(x,y))
            bPathEnd.setMousePressed(true);
        else {
            for (MyButton b : map.keySet())
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;

            }

        }
    }

    /** metoda mouseReleased resetuje wszstkie efekty wizualne przyciskow
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        bGrass.resetBooleans();
        bWater.resetBooleans();
        bPathEnd.resetBooleans();
        bPathStart.resetBooleans();
        for(MyButton b : map.keySet())
            b.resetBooleans();

    }

    /** metoda getStartpathImg zwraca punkt Start
     *
     * @return pathStart
     */
    public BufferedImage getStartpathImg() {
         return pathStart;
    }

    /** metoda getEndPathImg zwraca nam punkt End
     *
     * @return pathEnd
     */
    public BufferedImage getEndPathImg() {
        return pathEnd;
    }

}
