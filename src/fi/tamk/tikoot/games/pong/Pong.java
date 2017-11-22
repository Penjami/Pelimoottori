package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.GameApplication;
import fi.tamk.tikoot.pelimoottori.GameScene;
import fi.tamk.tikoot.pelimoottori.Settings;

public class Pong extends GameApplication {
    private PongScene pongScene = new PongScene();

    public static void main(String[] args) { launch(args); }

    @Override
    protected void setSettings(Settings settings) {
        gameScene = pongScene;
        settings.setTitle("TestGame");
        settings.setWidth(600);
        settings.setHeight(360);
    }
}
