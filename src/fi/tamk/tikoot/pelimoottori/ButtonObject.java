package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class ButtonObject extends GameObject {

    private Button button;

    public ButtonObject(String text, Scene scene) {
        super();
        button = new Button(text);
        scene.getRoot().getChildrenUnmodifiable().add(getButton());
    }

    public void draw(GraphicsContext gc) {
    }

    public Button getButton() {
        return button;
    }

    public void setButton(String text) {
        this.button = new Button(text);
    }
}
