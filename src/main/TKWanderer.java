package main;

import main.models.Tile;
import main.views.Board;

import java.awt.*;
import javax.swing.*;

public class TKWanderer extends JFrame {
    public static final int SCREEN_WIDTH = 11 * Tile.TILE_SIZE;
    public static final int SCREEN_HEIGHT = 9 * Tile.TILE_SIZE + 23;

    private TKWanderer() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);

        setTitle("main.TKWanderer");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            TKWanderer wanderer = new TKWanderer();
            wanderer.setVisible(true);
        });
    }
}
