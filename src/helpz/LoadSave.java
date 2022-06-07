package helpz;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/** klasa LoadSave sluzy do ladowania zpisanej mapy w trybie edycji **/
public class LoadSave {
/** metoda getSpriteAtlas sluzy do pobierania pliku atlas.png w ktorym sa wszystkie textury
 * @return img **/
    public static BufferedImage getSpriteAtlas(){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("atlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }

    public static void CreateFile(){
        File txtFile = new File("res/new_level.txt");

        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** metoda CreateFile  tworzy plik new_level.txt w ktory jest zapisana mapa zaprojektowana w trybie edit
     * @param  "String name, int [] idArr
     * @return nic nie zwraca**/
    public static void CreateLevel(String name, int [] idArr){

        File newLevel = new File("res/" + name + ".txt");

        if(newLevel.exists()){
            System.out.println("File " + name + " already exists");
            return;
        }else{
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WriteToFile(newLevel, idArr, new PathPoint(0,0), new PathPoint(0,0));

        }


    }
/** WriteToFile metoda ta edytuje plik txt dzieki czemu zapisuje zmiany mapy jakie dokonano poprzez tryb edycji
 * @param " f, int [] idArr, PathPoint start, PathPoint end"
 * @return nic nie zwraca**/
    private static void WriteToFile(File f, int [] idArr, PathPoint start, PathPoint end ){

        try {
            PrintWriter pw = new PrintWriter(f);

            for(Integer i : idArr)
                pw.println(i);
            pw.println(start.getxCord());
            pw.println(start.getyCord());
            pw.println(end.getxCord());
            pw.println(end.getyCord());


            pw.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * metoda SaveLevel
     * @param " name, idArr, start, end "
     * @return nic nie zwraca
     */
    public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint end){
        File levelFile = new File("res/" + name + ".txt");

        if(levelFile.exists()){
            WriteToFile(levelFile, Utilz.TwoDto1DintArr(idArr), start, end);

        }else{
            System.out.println("File " + name + " does not exists");
            return;
        }

    }

    /**
     * metoda ReadFromFile sluzy do przeskanowania pliku z mapa
     * @param file
     * @return
     */

    private static ArrayList <Integer> ReadFromFile(File file){
        ArrayList <Integer> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
            list.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  list;

    }

    /**
     * metoda GetLevelPathPoints sluzy do zapisywania punktow poczatku i konca obiektow typu enemy
     * @param name
     * @return
     */
    public static ArrayList<PathPoint> GetLevelPathPoints(String name){
        File lvlFile = new File("res/" + name + ".txt");
        if(lvlFile.exists()){

            ArrayList <Integer> list = ReadFromFile(lvlFile);
            ArrayList<PathPoint> points = new ArrayList<>();
            points.add(new PathPoint(list.get(400), list.get(401)));
            points.add(new PathPoint(list.get(402), list.get(403)));

            return points;

        }else {
            System.out.println("File: " + name + "does not exist");
            return null;
        }
    }

    /**
     * metoda zwraca zapisany plik mapy
     * @param name
     * @return Utilz.ArrayListTo2dint, null
     */
    public static int [][] GetLevelData(String name ){

        File lvlFile = new File("res/" + name + ".txt");
        if(lvlFile.exists()){

            ArrayList <Integer> list = ReadFromFile(lvlFile);
            return  Utilz.ArrayListTo2dint(list,20, 20 );


        }else {
            System.out.println("File: " + name + "does not exist");
            return null;
        }

    }
}
