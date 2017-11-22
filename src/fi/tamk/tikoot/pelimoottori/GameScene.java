package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

abstract public class GameScene {
    private GraphicsContext graphicsContext;
    private Scene scene;



    public void update(float time) {

    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void changeScene(GameScene scene) {
        
    }

    /**
     * Used to set all objects to start values.
     */
    abstract protected void launchProperties();
    /**
     * Do logic in this method
     *
     * @param time the time between frames.
     */
    abstract protected void update(double time);
    /**
     * Check collision in this method
     */
    abstract protected void collisions();
    /**
     * Draw all sprites in this method
     */
    abstract protected void draw();
    /**
     * Draw all sprites in this method
     */
    abstract protected void removeObjects();
}
