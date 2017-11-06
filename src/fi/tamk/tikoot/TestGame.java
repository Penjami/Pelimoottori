package fi.tamk.tikoot;

import fi.tamk.tikoot.Game.GameApplication;
import fi.tamk.tikoot.Game.Settings;

/**
 * Created by Penjami on 6.11.2017.
 */
public class TestGame extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void setSettings(Settings settings) {
        settings.setTitle("TestGame");
        settings.setWidth(640);
        settings.setHeight(400);
    }
    @Override
    protected void update() {

    }
    @Override
    protected void draw() {

    }
}
