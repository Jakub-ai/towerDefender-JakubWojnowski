package helpz;

import java.util.ArrayList;

/**
 * klasa Utilz klasa w ktorej sa metody matematyczne sluzace do mechaniki gry
 */
public class Utilz {
    /**
     * metoda ArrayListTo2dint przeliczajaca ArrayList na newArr 2wymiarowa
     * @param list
     * @param ySize
     * @param xSize
     * @return newArr
     */
    public static int [][] ArrayListTo2dint(ArrayList<Integer> list, int ySize, int xSize){

        int [][] newArr =  new int[ySize][xSize];

        for(int j = 0; j < newArr.length; j++)
            for(int i = 0; i < newArr[j].length; i++){
                int index = j*ySize + i;
                newArr[j][i] = list.get(index);

            }
        return newArr;

    }

    /**
     * metoda TwoDto1DintArr przelicza 2wymiarowa liste na 1wymiarowa liste
     * @param twoArr
     * @return oneArr
     */
    public static int[] TwoDto1DintArr(int [][] twoArr){
        int[] oneArr = new int[twoArr.length * twoArr[0].length];
        for(int j = 0; j < twoArr.length; j++)
            for(int i = 0; i < twoArr[j].length; i++){
                int index = j*twoArr.length + i;
                oneArr[index] = twoArr[j][i];

            }
        return oneArr;
    }

    /**
     * metoda GetHypoDistance dzieki ktorej jest obliczana odleglosc obiektu Enemy od Tower
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return Math.hypot(xDiff,yDiff)
     */
    public static int GetHypoDistance(float x1, float y1,float x2, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);
        return (int) Math.hypot(xDiff,yDiff);

    }

}
