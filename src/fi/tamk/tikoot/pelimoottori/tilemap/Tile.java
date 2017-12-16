package fi.tamk.tikoot.pelimoottori.tilemap;

public class Tile {

    private int tileLocX;
    private int tileLocY;
    private int tileSize;

    public Tile(int tileLocX, int tileLocY, int tileSize) {
        this.tileLocX = tileLocX;
        this.tileLocY = tileLocY;
        this.tileSize = tileSize;
    }
}
