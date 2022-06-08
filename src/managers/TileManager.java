package managers;

import helpz.ImageFix;
import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static helpz.Constants.Tiles.*;

/** klasa TileManager w ktorej sa zarzadzane wszystkie textury zwiazane z projektowaniem mapy
 *
 */
public class TileManager {

    public Tile GRASS,ROAD,ROAD_UPDW, ROAD_LT_CORNER, ROAD_RT_CORNER, ROAD_BL_CORNER,ROAD_BR_CORNER, WATER,WATERA,WATERB,WATERC, BL_WATER_CORNER, TL_WATER_CORNER,BR_WATER_CORNER,TR_WATER_CORNER,
            N_WATER_COASTLN, W_WATER_COASTLN, S_WATER_COASTLN, E_WATER_COASTLN, ES_ISLAND, WS_ISLAND, NW_ISLAND,NE_ISLAND;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> roadsS = new ArrayList<>();
    public ArrayList<Tile> roadsC = new ArrayList<>();
    public ArrayList<Tile> corners = new ArrayList<>();
    public ArrayList<Tile> beaches = new ArrayList<>();
    public ArrayList<Tile> islands = new ArrayList<>();

    /** konstruktor klasy TileManager
     *
     */
    public TileManager(){
        loadAtlas();
        createTiles();

    }

    /** metoda createTiles jest odpowiedzialna za stworzenie wszystkich textur mapy
     * textury sa podzielane na typy takie jak WATER_TILE czy ROAD_TILE oraz GRASS_TILE
     * kazdy typ textur ma wlasna ArrayList takie jak: roadsS, roadsC, corners, beaches, islands ktore nastepnie sa dodane do ArrayList tiles
     */
    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0),id++,GRASS_TILE));
        tiles.add(WATER = new Tile(getAniSprites(0, 0),id++, WATER_TILE));
        //ROADS
        roadsS.add(ROAD = new Tile(getSprite(8, 0),id++, ROAD_TILE));
        roadsS.add(ROAD_UPDW = new Tile(ImageFix.getRotImg(getSprite(8,0), 90),id++, ROAD_TILE));
        roadsC.add(ROAD_LT_CORNER = new Tile(getSprite(7,0), id++, ROAD_TILE));
        roadsC.add(ROAD_RT_CORNER = new Tile(ImageFix.getRotImg(getSprite(7,0),90),id++, ROAD_TILE));
        roadsC.add(ROAD_BR_CORNER = new Tile(ImageFix.getRotImg(getSprite(7,0),180),id++, ROAD_TILE));
        roadsC.add(ROAD_BL_CORNER = new Tile(ImageFix.getRotImg(getSprite(7,0),270),id++, ROAD_TILE));
        //Water Body

        corners.add(BL_WATER_CORNER = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite (5,0),0),id++,WATER_TILE));
        corners.add(BR_WATER_CORNER= new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(5, 0),270),id++,WATER_TILE));
        corners.add(TL_WATER_CORNER= new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(5,0),90),id++,WATER_TILE));
        corners.add(TR_WATER_CORNER= new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(5,0),180),id++,WATER_TILE));

        beaches.add(N_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(6,0),0),id++,WATER_TILE));
        beaches.add(S_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(6,0),180),id++, WATER_TILE));
        beaches.add(E_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(6,0),90),id++, WATER_TILE));
        beaches.add(W_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(6,0),270),id++, WATER_TILE));

        islands.add(NE_ISLAND = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(4,0),0),id++,WATER_TILE));
        islands.add(NW_ISLAND = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(4,0),90),id++, WATER_TILE));
        islands.add(WS_ISLAND = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(4,0),180),id++, WATER_TILE));
        islands.add(ES_ISLAND = new Tile(ImageFix.getBuildRotImg(getAniSprites(0,0),getSprite(4,0),270),id++, WATER_TILE));

        tiles.addAll(roadsS);
        tiles.addAll(roadsC);
        tiles.addAll(corners);
        tiles.addAll(beaches);
        tiles.addAll(islands);

    }

    /** metoda getImgs sluzy do laczenia dwoch textur
     *
     * @param firstX jest to X pierwszej textury z atlasu
     * @param firstY jest to y pierwszej textury z atlasu
     * @param secondX jest to X drugiej textury z atlasu
     * @param secondY jest to Y drugiej textury z atlasu
     * @return new BufferedImage[]
     */
    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY){
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX,secondY)};

    }

    /** metoda getTile zwraca id textury
     *
     * @param id id textury
     * @return id
     */
    public Tile getTile(int id){
        return tiles.get(id);

    }

    /** metoda laduje atlas
     * @return niczego nie zwraca
     */
    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    /** metoda zwraca id textury getSprite
     *
     * @param id id textury
     * @return tiles.get(id).getSprite()
     */
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    /** metoda getAniSprite zwraca index animacji
     *
     * @param id id textury
     * @param animationIndex index animacji
     * @return tiles.get(id).getSprite(animationIndex)
     */
    public BufferedImage getAniSprite(int id, int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }

    /** metoda getAniSprites towrzy tablice animacji
     *
     * @param xCord koordynat x
     * @param yCord koordynat y
     * @return arr
     */
    private BufferedImage[] getAniSprites(int xCord, int yCord){
        BufferedImage[] arr = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            arr[i] = getSprite(xCord + i, yCord);

        }
        return arr;
    }

    /** metoda getSprite okresla charakterystyke spritu szerokosc, wysokosc
     *
     * @param xCord
     * @param yCord
     * @return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32)
     */
    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

    /** metoda boolean isSpriteAnimation sprawdza id spritu jest odpowiednie dla animacji
     *
     * @param spriteID
     * @return tiles.get(spriteID).isAnimation()
     */
    public boolean isSpriteAnimation(int spriteID) {
       return tiles.get(spriteID).isAnimation();
    }

    /** metoda getRoadsS zwraca ArrayList roadsS
     *
     * @return roadsS
     */
    public ArrayList<Tile> getRoadsS() {
        return roadsS;
    }

    /** metoda getRoadsC zwraca roadsC
     *
     * @return roadsC
     */
    public ArrayList<Tile> getRoadsC() {
        return roadsC;
    }

    /** metoda getCorners zwraca corners
     *
     * @return corners
     */
    public ArrayList<Tile> getCorners() {
        return corners;
    }

    /** netoda getBeaches zwraca beaches
     *
     * @return beaches
     */
    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    /** metoda getIslands zwraca islands
     *
     * @return islands
     */
    public ArrayList<Tile> getIslands() {
        return islands;
    }


}
