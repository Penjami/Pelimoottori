package fi.tamk.tikoot.pelimoottori;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class UIButton extends Button {

    public UIButton(String text, Group uiRoot, double x, double y) {
        super(text);
        uiRoot.getChildren().add(this);
        Platform.runLater(() -> {
            setTranslateX(x-getWidth()/2);
            setTranslateY(y-getHeight()/2);
        });
    }
}
