package fi.tamk.tikoot.pelimoottori;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by Penjami on 5.11.2017.
 */
public class InputHandler {

    private Scene mainScene;
    private ArrayList<String> input;

    /**
     * Saves key and mouse presses and releases in an array.
     *
     * @param scene Scene is used to get the listener for registering key and mouse presses and releases.
     */
    public InputHandler(Scene scene) {
        mainScene = scene;

        input = new ArrayList<String>();

        mainScene.setOnKeyPressed((event) ->
            {
                String code = event.getCode().toString();
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        );

        mainScene.setOnKeyReleased((event) ->
            {
                String code = event.getCode().toString();
                input.remove( code );
            }
        );

        mainScene.setOnMousePressed((event) ->
            {
                String code = event.getButton().toString();
                System.out.println(event.getButton().toString());
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        );
        mainScene.setOnMouseReleased((event)->
            {
                String code = event.getButton().toString();
                input.remove( code );
            }
        );
    }

    /**
     * @return List containing all the key inputs from this frame.
     */
    public ArrayList<String> getInput() {
        return input;
    }

}
