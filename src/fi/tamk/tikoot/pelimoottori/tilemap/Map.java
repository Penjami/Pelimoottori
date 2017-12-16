package fi.tamk.tikoot.pelimoottori.tilemap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Map {

    private int[][] tiles;
    private TileImage tileImage;
    private int tileSize;
    private double mapX;
    private double mapY;
    private Affine affine = new Affine();

    public Map(String fileName, TileImage tileImage, double mapX, double mapY) {
        this.tileImage = tileImage;
        this.tileSize = tileImage.getTileSize();
        this.mapX = mapX;
        this.mapY = mapY;
        readMapToTiles(fileName);

    }

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

    public void setPosition(double mapX, double mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
    }

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

    public double getMapX() {
        return mapX;
    }

    public double getMapY() {
        return mapY;
    }
}
