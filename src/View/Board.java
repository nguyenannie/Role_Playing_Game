package View;

import Controller.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import static Model.Maze.MAZE_HEIGHT;
import static Model.Maze.MAZE_WIDTH;
import static Model.Tile.tileSize;

public class Board extends JPanel implements ActionListener {
    private GameController controller;
    private DrawStat drawStat;
    private DrawCharacter drawCharacter;
    private DrawMaze drawMaze;
    private final int DELAY = 100;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        controller = new GameController();
        drawStat = new DrawStat();
        drawMaze = new DrawMaze();
        drawCharacter = new DrawCharacter();

        drawMaze.setMaze(controller.getMaze());
        drawStat.setGameController(controller);

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        int boundLeft = TKWanderer.SCREEN_WIDTH / 2;
        int boundRight = MAZE_WIDTH * tileSize - TKWanderer.SCREEN_WIDTH / 2;

        int boundTop = TKWanderer.SCREEN_HEIGHT / 2;
        int boundBottom = MAZE_HEIGHT * tileSize - TKWanderer.SCREEN_HEIGHT / 2;

        int camXPos = clamp(controller.getHero().getX() * tileSize, boundLeft, boundRight);
        int camYPos = clamp( controller.getHero().getY() * tileSize, boundTop, boundBottom);

        int offSetX = - camXPos + TKWanderer.SCREEN_WIDTH/2;
        int offSetY = - camYPos + TKWanderer.SCREEN_HEIGHT/2 ;

        g.translate(offSetX, offSetY);

        drawMaze.draw(g);

        for(int i = 0; i < controller.getMonsterList().size(); i++) {
            drawCharacter.setCharacter(controller.getMonster(i));
            drawCharacter.draw(g);
        }

        drawCharacter.setCharacter(controller.getHero());
        drawCharacter.draw(g);

        g.translate(-offSetX, -offSetY);
        drawStat.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            controller.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            controller.keyPressed(e);

        }
    }

    private static int clamp(int x, int low, int high) {
        return Math.max(low, Math.min(high, x));
    }
}
