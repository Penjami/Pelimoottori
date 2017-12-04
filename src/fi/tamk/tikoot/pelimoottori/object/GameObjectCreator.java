package fi.tamk.tikoot.pelimoottori.object;

import fi.tamk.tikoot.pelimoottori.core.Settings;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;


/**
 * This class is used to create game objects for the game with predefined attributes.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class GameObjectCreator {

    /**
     * Creates a new gameObject with a rectangle shape.
     *
     * @param world The physics world of the game.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param x The X location of the rectangle.
     * @param y The Y location of the rectangle.
     * @param type The mass type of the entity.
     * @return The gameObject to return.
     */
    public GameObject createRectangleObject(World world, double width, double height, double x, double y, MassType type) {
        GameObject rectangle = new GameObject(world);
        rectangle.getBody().addFixture(Geometry.createRectangle(width/Settings.SCALE, height/Settings.SCALE));
        rectangle.getBody().setMass(type);
        rectangle.getBody().translate(x/ Settings.SCALE,y/Settings.SCALE);
        rectangle.setType(ObjectType.RECTANGLE);
        return rectangle;
    }

    /**
     * Creates a new gameObject with a circle shape.
     *
     * @param world The physics world of the game.
     * @param radius The radius of the circle.
     * @param x The X location of the circle.
     * @param y The Y location of the circle.
     * @param type The mass type of the entity.
     * @return The gameObject to return.
     */
    public GameObject createCircleObject( World world, double radius, double x, double y, MassType type) {
        GameObject circle = new GameObject(world);
        circle.getBody().addFixture(new Circle(radius/Settings.SCALE));
        circle.getBody().setMass(type);
        circle.getBody().translate(x/Settings.SCALE, y/Settings.SCALE);
        circle.setType(ObjectType.CIRCLE);
        return circle;
    }
}
