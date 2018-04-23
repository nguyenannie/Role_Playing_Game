package Model;

import javax.swing.*;
import java.awt.*;

public class Tile {
    public static final int tileSize = 72;

    private Image tileType;
    public boolean isSolid;

    Tile(String filePath, boolean isSolid) {
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
}
