package helpz;

import java.awt.*;
import java.awt.image.BufferedImage;
/** klasa imageFix okresla charakterystyke wszystkich textur uzytych w grze **/
public class ImageFix {
/** metoda getRotImg obraca texture, na przyklad metoda ta sluzy do obracania textury z poziomej drogi na pionowa droge **/
public static BufferedImage getRotImg(BufferedImage img, int rotAngle){
    int w = img.getWidth();
    int h = img.getHeight();

    BufferedImage newImg = new BufferedImage(w , h, img.getType());
    Graphics2D g2d = newImg.createGraphics();

    g2d.rotate(Math.toRadians(rotAngle),w/2,h/2);
    g2d.drawImage(img, 0,0,null);
    g2d.dispose();

    return newImg;


}

public static BufferedImage buildImg(BufferedImage[] imgs){

    int w = imgs[0].getWidth();
    int h = imgs[0].getHeight();

    BufferedImage newImg = new BufferedImage(w , h, imgs[0].getType());
    Graphics2D g2d = newImg.createGraphics();

    for(BufferedImage img : imgs){
        g2d.drawImage(img,0,0,null);
    }

    g2d.dispose();
    return newImg;
}
//rotates tiles
public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex ){
    int w = imgs[0].getWidth();
    int h = imgs[0].getHeight();

    BufferedImage newImg = new BufferedImage(w , h, imgs[0].getType());
    Graphics2D g2d = newImg.createGraphics();

for( int i =0; i < imgs.length; i++ ){
    if(rotAtIndex == i)
        g2d.rotate(Math.toRadians(rotAngle),w/2,h/2);
    g2d.drawImage(imgs[i],0,0,null);
    if(rotAtIndex == i)
        g2d.rotate(Math.toRadians(-rotAngle),w/2,h/2);

}

    g2d.dispose();
    return newImg;
}
//rotates second image + animations
    /** metoda getBuildRotImg obraca nam drugi obrazek textury oraz dodaje animacje.
     * @return  arr **/
    public static BufferedImage[] getBuildRotImg(BufferedImage[] imgs, BufferedImage secondImage, int rotAngle ){
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();
        BufferedImage[] arr = new BufferedImage[imgs.length];
        for(int i = 0; i < imgs.length; i ++ ) {
            BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
            Graphics2D g2d = newImg.createGraphics();

            g2d.drawImage(imgs[i],0,0,null);
            g2d.rotate(Math.toRadians(rotAngle),w/2,h/2);
            g2d.drawImage(secondImage,0,0,null);
            g2d.dispose();

            arr[i] = newImg;
        }
        return arr;
    }



}
