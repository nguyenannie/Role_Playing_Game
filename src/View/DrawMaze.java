package View;

import Model.Maze;
import Model.Tile;
import java.awt.*;
import static Model.Maze.FLOOR;
import static Model.Maze.MAZE_HEIGHT;
import static Model.Maze.MAZE_WIDTH;

public class DrawMaze implements Drawable {

    private Maze maze;

    void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < MAZE_WIDTH; i ++) {
            for (int j = 0; j < MAZE_HEIGHT; j ++) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(maze.getTile(i,j).orElse(FLOOR).getTileType(),
                        i * Tile.tileSize,
                        j * Tile.tileSize,
                        null);
            }
        }
    }
}
