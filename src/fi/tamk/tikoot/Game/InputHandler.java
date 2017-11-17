package fi.tamk.tikoot.Game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Created by Penjami on 5.11.2017.
 */
public class InputHandler {

    private Scene mainScene;
    private ArrayList<String> input;

    /**
     * Saves key presses and releases in an array.
     *
     * @param scene Scene is used to get the listener for registering key presses.
     */
    public InputHandler(Scene scene) {
        mainScene = scene;

        input = new ArrayList<String>();

        mainScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        mainScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });
    }

    /**
     * Returns the recorded inputs.
     *
     * @return Returns the recorded inputs.
     */
    public ArrayList<String> getInput() {
        return input;
    }

}
