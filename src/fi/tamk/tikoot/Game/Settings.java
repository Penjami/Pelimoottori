package fi.tamk.tikoot.Game;

/**
 * Created by Penjami on 6.11.2017.
 */
public class Settings {

    private String title = "Untitled";
    private int width = 800;
    private int height = 600;

    public Settings(){}

    public Settings(String title, int width, int height) {
        setTitle(title);
        setWidth(width);
        setHeight(height);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
