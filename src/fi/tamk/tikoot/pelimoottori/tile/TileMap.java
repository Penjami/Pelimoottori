package fi.tamk.tikoot.pelimoottori.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is used to create Tilemaps for the game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
 */
public class TileMap {

    private int[][] tiles;
    private TileImage tileImage;
    private int tileSize;
    private double mapX;
    private double mapY;
    private Affine affine = new Affine();

    /**
     * The constructor for the TileMap class.
     *
     * @param fileName Location and name of the tile file.
     * @param tileImage Image that includes all the tiles sprites.
     * @param mapX The horizontal coordinate of this map.
     * @param mapY The vertical coordinate of this map.
     */
    public TileMap(String fileName, TileImage tileImage, double mapX, double mapY) {
        this.tileImage = tileImage;
        this.tileSize = tileImage.getTileSize();
        this.mapX = mapX;
        this.mapY = mapY;
        readMapToTiles(fileName);

    }

    /**
     * Used to render all the tiles from this tile.
     *
     * @param gc The GraphicContext that is used to render the GameObject onto the scene.
     */
    public void draw(GraphicsContext gc) {
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[0].length; j++) {
                gc.save();
                Rotate r = new Rotate(180, j*tileSize - tileSize/2,
                        (tiles.length-i-1)*tileSize - tileSize/2);
                affine.setToTransform(r);
                gc.transform(affine);
                gc.drawImage(tileImage.getTile(tiles[i][j]), j*tileSize - mapX, mapY + (tiles.length-i-3)*tileSize,
                        -tileSize, tileSize);
                gc.restore();
            }
        }
    }

    /**
     * Used to set the position of the map object.
     *
     * @param mapX The horizontal coordinate of this map.
     * @param mapY The vertical coordinate of this map.
     */
    public void setPosition(double mapX, double mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
    }

    /**
     * converts a text file to 2d int array that contains the number for a tile.
     *
     * @param fileName Location and name of the tile file.
     */
    private void readMapToTiles(String fileName) {
        try {
            tiles = Files.lines(Paths.get(fileName))
                                    .map(item -> item.chars()
                                    .filter(i -> (char) i != ' ')
                                    .map(Character::getNumericValue).toArray())
                                    .toArray(int[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return The horizontal coordinate of this map.
     */
    public double getMapX() {
        return mapX;
    }

    /**
     * @return The vertical coordinate of this map.
     */
    public double getMapY() {
        return mapY;
    }
}
