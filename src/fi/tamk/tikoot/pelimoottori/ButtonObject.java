package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class ButtonObject extends GameObject {

    private Button button;

    public ButtonObject(String text, Group uiRoot, double x, double y) {
        super();
        button = new Button(text);
        button.setTranslateX(x);
        button.setTranslateY(y);
        uiRoot.getChildren().add(getButton());
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
