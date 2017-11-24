package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class ButtonObject extends Button {

    public ButtonObject(String text, Group uiRoot, double x, double y) {
        super(text);
        setTranslateX(x);
        setTranslateY(y);
        uiRoot.getChildren().add(this);
    }
}
