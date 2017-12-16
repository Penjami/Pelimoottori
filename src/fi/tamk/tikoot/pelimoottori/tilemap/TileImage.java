package fi.tamk.tikoot.pelimoottori.tilemap;


import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TileImage {

    private Image tileImg;
    private Image[][] tileSheet;
    private int tileSize;
    private int tilesX;
    private int tilesY;

    public TileImage(String fileName, int tileSize, int tilesX, int tilesY ) {
        tileImg = new Image(fileName);
        this.tileSize = tileSize;
        this.tilesX = tilesX;
        this.tilesY = tilesY;
        tileSheet = new Image[tilesY][tilesX];
        createTileSheet();

    }

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

    public Image getTileImg() {
        return tileImg;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getTilesX() {
        return tilesX;
    }

    public int getTilesY() {
        return tilesY;
    }
}
