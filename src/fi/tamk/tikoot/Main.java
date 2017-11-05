package fi.tamk.tikoot;

import fi.tamk.tikoot.Game.Game;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        try {
            game.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
