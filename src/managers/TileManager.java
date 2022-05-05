package managers;

import helpz.ImageFix;
import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    public TileManager(){
        loadAtlas();
        createTiles();

    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0),id++,"Grass"));
        tiles.add(WATER = new Tile(getSprite(0, 0),id++, "Water"));
        //ROADS
        roadsS.add(ROAD = new Tile(getSprite(8, 0),id++, "Road"));
        roadsS.add(ROAD_UPDW = new Tile(ImageFix.getRotImg(getSprite(8,0), 90),id++, "ROAD_UPDW"));
        roadsC.add(ROAD_LT_CORNER = new Tile(getSprite(7,0), id++, "ROAD_LT_CORNER"));
        roadsC.add(ROAD_RT_CORNER = new Tile(ImageFix.getRotImg(getSprite(7,0),90),id++, "ROAD_RT_CORNER"));
        roadsC.add(ROAD_BR_CORNER = new Tile(ImageFix.getRotImg(getSprite(7,0),180),id++, "ROAD_BR_CORNER"));
        roadsC.add(ROAD_BL_CORNER = new Tile(ImageFix.getRotImg(getSprite(7,0),270),id++, "ROAD_BL_CORNER"));
        //Water Body

//        tiles.add(WATERA = new Tile(getSprite(1, 0),id++, "WATERA"));
//        tiles.add(WATERB = new Tile(getSprite(2, 0),id++, "WATERB"));
//        tiles.add(WATERC = new Tile(getSprite(3, 0),id++, "WATERC"));
        corners.add(BL_WATER_CORNER = new Tile(ImageFix.buildImg(getImgs(0,0,5,0)),id++,"BL_Water_Corner"));
        corners.add(BR_WATER_CORNER= new Tile(ImageFix.getBuildRotImg(getImgs(0,0,5,0),270,1),id++,"BR_WATER_CORNER"));
        corners.add(TL_WATER_CORNER= new Tile(ImageFix.getBuildRotImg(getImgs(0,0,5,0),90,1),id++,"TL_WATER_CORNER"));
        corners.add(TR_WATER_CORNER= new Tile(ImageFix.getBuildRotImg(getImgs(0,0,5,0),180,1),id++,"TR_WATER_CORNER"));
        beaches.add(N_WATER_COASTLN = new Tile(ImageFix.buildImg(getImgs(0,0,6,0)),id++,"N_WATER_COASTLN"));
        beaches.add(S_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getImgs(0,0,6,0),180,1),id++, "S_WATER_COASTLN"));
        beaches.add(E_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getImgs(0,0,6,0),90,1),id++, "E_WATER_COASTLN"));
        beaches.add(W_WATER_COASTLN = new Tile(ImageFix.getBuildRotImg(getImgs(0,0,6,0),270,1),id++, "W_WATER_COASTLN"));
        islands.add(NE_ISLAND = new Tile(ImageFix.buildImg(getImgs(0,0,4,0)),id++,"NE_ISLAND"));
        islands.add(NW_ISLAND = new Tile(ImageFix.getBuildRotImg(getImgs(0,0,4,0),90,1),id++, "NW_ISLAND"));
        islands.add(WS_ISLAND = new Tile(ImageFix.getBuildRotImg(getImgs(0,0,4,0),180,1),id++, "WS_ISLAND"));
        islands.add(ES_ISLAND = new Tile(ImageFix.getBuildRotImg(getImgs(0,0,4,0),270,1),id++, "ES_ISLAND"));

        tiles.addAll(roadsS);
        tiles.addAll(roadsC);
        tiles.addAll(corners);
        tiles.addAll(beaches);
        tiles.addAll(islands);

    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY){
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX,secondY)};

    }
    public Tile getTile(int id){
        return tiles.get(id);

    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);

    }

    public ArrayList<Tile> getRoadsS() {
        return roadsS;
    }

    public ArrayList<Tile> getRoadsC() {
        return roadsC;
    }

    public ArrayList<Tile> getCorners() {
        return corners;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public ArrayList<Tile> getIslands() {
        return islands;
    }
}
