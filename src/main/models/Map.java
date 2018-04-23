package main.models;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Map {

    private Tile[][] tiles = new Tile[10][11];
    private Path mapPath;
    private List<String> mapData;
    private static final String PATH = "resources/MapData";

    public Map() {
        mapPath = Paths.get(PATH);
        mapData = new ArrayList<>();
        getMapData();

        Tile wall = new Tile("wall.gif",true);
        Tile floor = new Tile("floor.gif",false);

        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 11; j ++){
                if(getMapData().get(i).charAt(j) == '0'){
                    tiles[i][j] = floor;
                } else if (getMapData().get(i).charAt(j) == '1'){
                    tiles[i][j] = wall;
                }
            }
        }
    }

    public List<String> getMapData() {
        try {
            mapData = Files.readAllLines(mapPath);
        } catch (Exception e){
            e.getStackTrace();
        }
        return mapData;
    }

    public Tile getTile(int x, int y){
        if(x < 0 || x > 9 || y < 0 || y > 10) {
            return new Tile("wall.gif",true);
        } else {
            return tiles[x][y];
        }
    }

    public void drawBackground(Graphics g){
        for(int i = 0; i < 10; i ++){
            for (int j = 0; j < 11; j ++){
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(getTile(i,j).getTileType(),i * Tile.TILE_SIZE,j * Tile.TILE_SIZE, null);
            }
        }
    }

}

