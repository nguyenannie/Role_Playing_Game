package Model;

import javax.swing.*;
import java.util.Random;

public class Monster extends Character {
    private final String monsterImage = "skeleton.gif";
    private boolean moveLastRound;
    private boolean hasKey;

    Monster(){
    }

    public Monster(int xPos, int yPos, int level, boolean hasKey) {
        this.level = level;
        maxHealthPoint = 2 * level * rollDice();
        defendPoint = (int)Math.ceil(level * rollDice() / 2.0);
        strikePoint = level * rollDice();
        healthPoint = maxHealthPoint;
        this.hasKey = hasKey;
        initCharacter(xPos,yPos);
    }
    
    public void move(Maze maze){
        int newX, newY;

        do {
            newX = x + stepRandom();
            newY = y + stepRandom();
        } while(maze.getTile(newX,newY).orElse(Maze.WALL).isSolid);

        x = newX;
        y = newY;
    }

    public void takeTurn(Maze maze){
        if(!moveLastRound) {
            move(maze);
            moveLastRound = true;
        } else {
            moveLastRound = false;
        }
    }

    private int stepRandom(){
        int r = new Random().nextInt(3);
        return r - 1;
    }

    void initCharacter(int xPos,int yPos){
        ImageIcon icon = new ImageIcon(monsterImage);
        image = icon.getImage();
        x = xPos;
        y = yPos;
    }
}
