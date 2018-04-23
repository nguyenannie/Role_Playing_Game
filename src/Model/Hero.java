package Model;

import java.util.Random;

public class Hero extends Character {
    private static final String faceDown = "hero-down.gif";
    private static final String faceRight = "hero-right.gif";
    private static final String faceLeft = "hero-left.gif";
    private static final String faceUp = "hero-up.gif";

    public enum Directions {
        UP, DOWN, LEFT, RIGHT;
    }

    public Hero() {
        maxHealthPoint = 20 + 3 * rollDice();
        defendPoint = 2 * rollDice();
        strikePoint = 5 + rollDice();
        healthPoint = maxHealthPoint;
        initCharacter();
    }

    public void initCharacter(){
        setImage(faceDown);
        x = 0;
        y = 0;
    }

    public void move(Maze maze, Directions dir){
        int newX = x, newY = y;

        switch (dir) {
            case UP:
                newY = y - 1;
                setImage(faceUp);
                break;

            case DOWN:
                newY = y + 1;
                setImage(faceDown);
                break;

            case LEFT:
                newX = x - 1;
                setImage(faceLeft);
                break;

            case RIGHT:
                newX = x + 1;
                setImage(faceRight);
                break;
        }

        if(!maze.getTile(newX,newY).orElse(Maze.WALL).isSolid && !this.isDead()){
            x = newX;
            y = newY;
        }

        if(maze.getTile(newX,newY).orElse(Maze.WALL).isSolid) {
        }
    }

    public void levelUp(){
        int chance = new Random().nextInt(10);
        if(chance == 0) {
            healthPoint = maxHealthPoint;
        } else if(chance <= 4) {
            setHealthPoint(healthPoint + healthPoint / 3);
        } else {
            setHealthPoint(healthPoint + healthPoint / 10);
        }
    }

}


