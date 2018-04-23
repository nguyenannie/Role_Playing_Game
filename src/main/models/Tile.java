package main.models;

import javax.swing.*;
import java.awt.*;

public class Tile {

    public static final int TILE_SIZE = 72;

    private Image tileType;
    private boolean isSolid;

    public Tile(String filePath, boolean isSolid) {
        this.isSolid = isSolid;
        setTileType(filePath);
    }

    private void setTileType(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        this.tileType = icon.getImage();
    }

    public Image getTileType() {
        return tileType;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

}
