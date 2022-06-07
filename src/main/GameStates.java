package main;

import scenes.GameOver;

/** klasa enum GameStates ustalajaca gameState
 *
 */
public enum GameStates {
    PLAYING,
    MENU,
    SETTINGS,
    EDIT,
    GAME_OVER;

    public static GameStates gameState = MENU;

    /** metoda SetGameState ustala jako Ganestate wejsciowy Menu
     *
     * @param state
     * @reurn nic nie zwraca
     */
    public static void SetGameState(GameStates state){
        gameState = state;
    }

}
