package main;

import scenes.GameOver;

public enum GameStates {
    PLAYING,
    MENU,
    SETTINGS,
    EDIT,
    GAME_OVER;

    public static GameStates gameState = MENU;

    public static void SetGameState(GameStates state){
        gameState = state;
    }

}
