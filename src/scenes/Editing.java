package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import UI.ToolBar;

import static helpz.Constants.Tiles.ROAD_TILE;

/** klasa editing jest to tryb w ktorym gracz towrzy wlasna mape
 * sa tutaj wszystkie metody zwiazane z edycja mapy
 */
public class Editing extends GameScene implements SceneMethods{


    private int[][] lvl;

    private Tile selectedTile;

    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private ToolBar toolBar;
    private PathPoint start,end;

    /** konstruktor klasy Editing
     *
     * @param game jest superklasa dal Editing
     */
    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        toolBar = new ToolBar(0,640,640, 160, this);
    }

    /** metoda loadDefaultLevel laduje poczatkowa mape przed edycja przez gracza
     *
     */
    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    /** metoda update aktualizuje czas
     *
     */
    public void update(){
        updateTick();
    }

    /** metoda render jest odpowiedzialna za renderowanie grafiki
     *
     * @param g Graphics
     */
    @Override
    public void render(Graphics g) {

        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
        drawPathPoint(g);
    }

    /** drawPathPoint metoda ta sluzy do umieszczania path points na mapie
     * dokladnie path points to sa dwa punkty start i end dla obiektow enemy w ktorym miejscu maja sie pojawic a gdzie jest koniec
     *
     * @param g
     */
    private void drawPathPoint(Graphics g) {
        if(start != null){
            g.drawImage(toolBar.getStartpathImg(),start.getxCord()*32, start.getyCord()*32,32,32,null );

        }
        if(end != null){
            g.drawImage(toolBar.getEndPathImg(),end.getxCord()*32, end.getyCord()*32,32,32,null );


        }
    }

    /** metoda drawLevel sluzy  wyswietlania mapy
     *
     * @param g graphics
     */
    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if(isAnimation(id)){
                    g.drawImage(getSprite(id,animationIndex), x * 32, y * 32, null);
                }else
                g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    /** metoda saveLevel sluzy do zapisywania edytowanej mapy
     *
     */
    public void saveLevel(){
        LoadSave.SaveLevel("new_level", lvl, start, end);
        game.getPlaying().setLevel(lvl);
    }

    /** metoda drawSelectedTile sluzy do umieszczania na mapie wybranej textury
     *
     * @param g graphics
     */
    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(),mouseX,mouseY,32,32,null);
        }
    }

    /** metoda setSelectedTile jest powiazana z rotacja textury sluzy do potwierdzenia ze to jest ta textura ktora wybrano
     *
     * @param tile
     */
    public void setSelectedTile(Tile tile){
        this.selectedTile = tile;
        drawSelect = true;
    }

    /** metoda changeTile sluzy do wybrania nowej textury kiedy mamy juz jedna wybrana
     *
     * @param x koordynat z atlasu
     * @param y koordynat z atlasu
     */
    private void changeTile(int x, int y) {

        if(selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if (selectedTile.getId() >= 0) {


                if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;

                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = selectedTile.getId();

                lvl[tileY][tileX] = selectedTile.getId();
            }else{
                int id = lvl[tileY][tileX];
                if(game.getTileManager().getTile(id).getTileType() == ROAD_TILE){

                    if(selectedTile.getId() == -1)
                        start = new PathPoint(tileX,tileY);
                    else
                        end = new PathPoint(tileX,tileY);
                }

            }
        }
    }

    /** metoda mouseclicked zajmuje sie zdarzeniem mouseclicked
     * przekierowuje do toolbar lub zmienia texture kiedy zdarzenie mialo miejsce na mapie
     * @param x
     * @param y
     */
    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 640){
            toolBar.mouseClicked(x,y);
        }else{
            changeTile(mouseX, mouseY);
        }


    }

    /** metoda mouseMovedzajmuje sie zdarzeniem mousemoved przkierowuje do toolbar
     * lub jesli zdarzenie ma miejsce na mapie podczas kliknieciu i przeciagnieciu myszki rysujemy wybrana texture
     * @param x
     * @param y
     */
    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 640){
            toolBar.mouseMoved(x,y);
            drawSelect = false;

        }else{
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;

        }

    }

    /** mousepressed zdarzenie to ma miejsce w toolbar
     *
     * @param x koordynaty na ekranie
     * @param y koordynaty na ekranie
     */
    @Override
    public void mousePressed(int x, int y) {
        if(y>=640)
            toolBar.mousePressed(x,y);

    }

    /** zdarzenie mousereleased czyli puszczenie przycisku myszy
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseReleased(int x, int y) {

            toolBar.mouseReleased(x,y);

    }

    /** metoda mouseDragged metoda przeciagniecia
     *
     * @param x koordynaty
     * @param y koordynaty
     */
    @Override
    public void mouseDragged(int x, int y) {
        if(y >= 640){

        }else{
            changeTile(x, y);
        }

    }

    /** zdarzenie key pressed "R" po wybraniu textury i wcisnieciu R texturasie obraca
     *
     * @param e keyEvent
     */
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_R)
            toolBar.rotateSprite();

    }
}
