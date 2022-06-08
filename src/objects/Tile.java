package objects;

import java.awt.image.BufferedImage;

/** klasa Tile jest odpowiedzialan za charakterystyke obiektu Tile
 *
 */
public class Tile {
    public BufferedImage[] sprite;
    private int id, tileType;
    private String name;

    /** konstruktor klasy Tile
     *
     * @param sprite textura
     * @param id id textury
     * @param tileType typ textury
     */
    public Tile(BufferedImage sprite, int id,int tileType){
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.id = id;
        this.tileType = tileType;

    }

    /** drugi konstruktor klasy Tile
     *
     * @param sprite textura
     * @param id id textury
     * @param tileType typ textury
     */
    public Tile(BufferedImage[] sprite, int id,int tileType){
        this.sprite = sprite;
        this.tileType = tileType;
        this.id = id;

    }

    /** metoda getTileType zwraca typ textury
     *
     * @return tileType
     */
    public int getTileType(){
        return tileType;
    }

    /** getSprite metoda ta zwraca animacje
     *
     * @param animationIndex
     * @return sprite[animationIndex]
     */
    public BufferedImage getSprite(int animationIndex){
        return sprite[animationIndex];
    }

    /** metoda getSprite zwraca texture
     *
     * @return sprite[0]
     */
    public BufferedImage getSprite(){
        return sprite[0];
    }

    /** metoda ta sprawdza czy textura powinna miec animacje
     *
     * @return sprite.length > 1
     */
    public boolean isAnimation(){
        return sprite.length > 1;
    }

    /** metoda geId zwraca id textury
     *
     * @return id
     */
    public int getId() {
        return id;
    }


}
