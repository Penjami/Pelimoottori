package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Vector2;

abstract public class GameScene {
    private GraphicsContext graphicsContext;
    private Canvas gameCanvas;
    private Group uiRoot;
    private Scene scene;
    private InputHandler inputHandler;
    public World world = new World();


    public GameScene(Settings settings) {
        world.setGravity(new Vector2(0,-9));
        Group root = new Group();
        uiRoot = new Group();
        Scale s = new Scale(1, -1);
        Translate t = new Translate(0,-settings.getHeight());
        gameCanvas = new Canvas(settings.getWidth() + 20,settings.getHeight() + 20);
        setGraphicsContext(gameCanvas.getGraphicsContext2D());
        getGraphicsContext().setFont(Font.font(30));
        gameCanvas.getTransforms().addAll(s,t);
        root.getChildren().addAll(gameCanvas, uiRoot);
        setScene(new Scene(root,settings.getWidth(),settings.getHeight()));
    }

    public void loop(double time) {
        world.updatev(time);
        update(time);
        collisions();
        draw();
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

    public void changeGameScene(GameScene scene) {

    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
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

    /**
     * @return Ui root used for ui elements.
     */
    public Group getUiRoot() {
        return uiRoot;
    }
    public Canvas getGameCanvas() {
        return gameCanvas;
    }

}
