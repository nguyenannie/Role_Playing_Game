package main.views;

import main.models.Maze;
import main.models.Tile;
import java.awt.*;
import static main.models.Maze.FLOOR;
import static main.models.Maze.MAZE_HEIGHT;
import static main.models.Maze.MAZE_WIDTH;

public class DrawMaze implements Drawable {

    private Maze maze;

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < MAZE_WIDTH; i ++) {
            for (int j = 0; j < MAZE_HEIGHT; j ++) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(maze.getTile(i,j).orElse(FLOOR).getTileType(),
                        i * Tile.TILE_SIZE,
                        j * Tile.TILE_SIZE,
                        null);
            }
        }
    }

}
