package fi.tamk.tikoot.pelimoottori.tile;


import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * This class is used to create the tilesheet that is used for a tileMap.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class TileImage {

    private Image tileImg;
    private Image[][] tileSheet;
    private int tileSize;
    private int tilesX;
    private int tilesY;

    /**
     * The default constructor for the TileImage class.
     *
     * @param fileName Name and location of the Image file.
     * @param tileSize Size of one tile in pixels.
     * @param tilesX How many tiles are horizontally in the image.
     * @param tilesY How many tiles are vertically in the image.
     */
    public TileImage(String fileName, int tileSize, int tilesX, int tilesY ) {
        tileImg = new Image(fileName);
        this.tileSize = tileSize;
        this.tilesX = tilesX;
        this.tilesY = tilesY;
        tileSheet = new Image[tilesY][tilesX];
        createTileSheet();

    }

    /**
     * splits the tileImg into tiles based on the amount of tiles and the tileSize.
     */
    private void createTileSheet() {
        ImageView imageView = new ImageView(tileImg);

        for(int i = 0; i < tilesY; i++) {
            for(int j = 0; j < tilesX; j++) {
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);
                parameters.setViewport(new Rectangle2D(j * tileSize, i*tileSize, tileSize, tileSize));

                WritableImage tile = new WritableImage(tileSize, tileSize);
                imageView.snapshot(parameters, tile);
                tileSheet[i][j] = tile;
            }
        }
    }

    /**
     * @param num Number that is a id for a tile.
     * @return returns the tile that corresponds the number
     */
    public Image getTile(int num) {
        int x = 0;
        for(int i = 0; i < tilesY; i++) {
            for(int j = 0; j < tilesX; j++) {
                if(x == num){
                    return tileSheet[i][j];
                }
                x++;
            }
        }
        return null;
    }

    /**
     * @return The tile sheet image.
     */
    public Image getTileImg() {
        return tileImg;
    }

    /**
     * @return The size of a tile in pixels.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * @return How many tiles there are horizontally.
     */
    public int getTilesX() {
        return tilesX;
    }

    /**
     * @return How many tiles there are vertically.
     */
    public int getTilesY() {
        return tilesY;
    }
}
