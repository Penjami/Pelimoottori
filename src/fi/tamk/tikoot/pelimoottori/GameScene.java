package fi.tamk.tikoot.pelimoottori;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.dyn4j.dynamics.World;

abstract public class GameScene {
    private GraphicsContext graphicsContext;
    private Canvas gameCanvas;
    private Group uiRoot;
    private Scene scene;
    private InputHandler inputHandler;
    private Settings settings;
    private GameApplication app;
    public World world = new World();

    public GameScene(Settings settings, GameApplication app) {
        this.app = app;
        this.settings = settings;
        Pane root = new Pane();
        uiRoot = new Group();
        Scale s = new Scale(1, -1);
        Translate t = new Translate(0,-settings.getHeight());
        gameCanvas = new Canvas(settings.getWidth(),settings.getHeight());
        gameCanvas.setWidth(settings.getWidth());
        gameCanvas.setHeight(settings.getHeight());
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
        app.changeScene(scene);
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

    public Settings getSettings() {
        return settings;
    }

    public GameApplication getApp() {
        return app;
    }
}
