package fi.tamk.tikoot.games.pong;

import fi.tamk.tikoot.pelimoottori.GameApplication;
import fi.tamk.tikoot.pelimoottori.GameScene;
import fi.tamk.tikoot.pelimoottori.Settings;

public class Pong extends GameApplication {
    private PongScene pongScene;

    public static void main(String[] args) { launch(args); }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("TestGame");
        settings.setWidth(600);
        settings.setHeight(360);
        gameScene = new MenuScene(settings, this);
    }
}
