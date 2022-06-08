package managers;

import events.Wave;
import scenes.Playing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/** klasa WaveManager zajmuje sie mechanika dzilania eventu wave czyli fali atakow obiektow typu enemy
 *
 */
public class WaveManager {

    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    private boolean waveStartTimer,waveTickTimerOver;

    /** konstruktor klasy wacemanager
     *
     * @param playing jest to odwolanie do sekwencji playing czyli w momencie trybu playing wave zacznie dzialac
     */
    public WaveManager(Playing playing){
        this.playing = playing;
        createWaves();

    }

    /** update jest to metoda aktualizowania czasu
     *
     */
    public void update(){
        if(enemySpawnTick < enemySpawnTickLimit)
        enemySpawnTick++;
        if(waveStartTimer){
            waveTick++;
            if(waveTick >= waveTickLimit){

                waveTickTimerOver = true;
            }

        }
    }

    /** increaseWaveIndex ta metoda zajmuje sie zwiekszaniem licznika fal
     *
     */
    public void increaseWaveIndex(){
        waveIndex++;
        waveTickTimerOver = false;
        waveStartTimer = false;
    }

    /** boolean isWaveTimerOver jest to metoda sprawdzajaca czy czas minal czas pomiedzy falami
     *
     * @return waveTickTimerOver
     */
    public boolean isWaveTimerOver() {
        return waveTickTimerOver;

    }

    /** metoda startWaveTimer metoda dzieki ktorej licznik czasu zaczyna biec
     *
     */
    public void startWaveTimer() {
        waveStartTimer = true;

    }

    /** metoda zwraca index enemy zwiekszony o 1
     *
     * @return enemyIndex++
     */
    public int getNextEnemy(){
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);

    }

    /** metoda createWaves jest odpowiedzialna za ilosc fal
     *
     */
    private void createWaves() {
        for (int j = 0; j < 3; j++)
        for(int i = 0; i < 4; i++ )
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(i,i,0,1,2,3,i,0,i,2))));
    }

    /** metoda getWaves zwraca ArrayList waves
     *
     * @return waves
     */
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    /** metoda boolean isTimeForNewEnemy sprawdza czy moze sie pojawic kolejny przecinik
     *
     * @return enemySpawnTick >= enemySpawnTickLimit
     */
    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;


    }

    /** metoda boolean isThereMoreEnemiesInWave sprawdza czy sa przeciwnicy na mapie
     *
     * @return enemyIndex < waves.get(waveIndex).getEnemyList().size()
     */
    public boolean isThereMoreEnemiesInWave(){
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    /** metoda boolean isThereMoreWaves() sprawdza czy jest wiecej fal do spwanowania
     *
     * @return waveIndex + 1 < waves.size()
     */
    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();
    }

    /** metoda resetEnemyIndex resetuje index enemy
     *
     */
    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    /** metoda getWaveIndex zwraca index fali
     *
     * @return waveIndex
     */
    public int getWaveIndex() {
        return waveIndex;
    }

    /** metoda getTimeLeft zwraca liczbe sekund przerwy
     *
     * @return ticksLeft / 60.0f
     */
    public float getTimeLeft(){
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60.0f;
    }

    /** boolean isWaveTimerStarted sprawdza czy zgaar zaczal liczyc
     *
     * @return waveStartTimer
     */
    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }

    /** metoda reset resetuje wszystko co zwiazane z wave
     *
     */
    public void reset(){
        waves.clear();
        createWaves();

        enemyIndex = 0;
        waveIndex = 0;
        waveStartTimer = false;
        waveTickTimerOver = false;
        waveTick = 0;
        enemySpawnTick = enemySpawnTickLimit;
    }
}
