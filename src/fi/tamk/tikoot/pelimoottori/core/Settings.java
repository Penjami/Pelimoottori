package fi.tamk.tikoot.pelimoottori.core;

/**
 * This class is used to set all the base settings for the game application.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class Settings {

    private String title = "Untitled";
    private int width = 800;
    private int height = 600;
    //pixel to physics ratio
    final public static double SCALE = 64.0;

    /**
     * Constructor that uses default settings.
     */
    public Settings(){}

    /**
     * Constructor to set the settings for the game.
     *
     * @param title The title for the game.
     * @param width The width of the gamescreen in pixels.
     * @param height The height of the gamescreen in pixels.
     */
    public Settings(String title, int width, int height) {
        setTitle(title);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Used to get the title of the game.
     *
     * @return Returns the title of the game.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Used to set the title of the game.
     *
     * @param title The title for the game.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Used to get the window width in pixels of the game.
     *
     * @return The width of the gamescreen in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Used to set the window width in pixels of the game.
     *
     * @param width Width of the gamescreen in pixels.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Used to get the window height in pixels of the game.
     *
     * @return The height of the gamescreen in pixels.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Used to set the window height in pixels of the game.
     *
     * @param height Height of the gamescreen in pixels.
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
