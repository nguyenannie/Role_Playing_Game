package main.models;

import java.util.Random;

public class Hero extends Character {

    private static final String FACE_DOWN = "/Users/annie/MyGithubProjects/TkWanderer/src/resources/images/hero-down.gif";
    private static final String FACE_RIGHT = "/Users/annie/MyGithubProjects/TkWanderer/src/resources/images/hero-right.gif";
    private static final String FACE_LEFT = "/Users/annie/MyGithubProjects/TkWanderer/src/resources/images/hero-left.gif";
    private static final String FACE_UP = "/Users/annie/MyGithubProjects/TkWanderer/src/resources/images/hero-up.gif";

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

    public void initCharacter() {
        setImage(FACE_DOWN);
        x = 0;
        y = 0;
    }

    public void move(Maze maze, Directions dir){
        int newX = x;
        int newY = y;

        switch (dir) {
            case UP:
                newY = y - 1;
                setImage(FACE_UP);
                break;

            case DOWN:
                newY = y + 1;
                setImage(FACE_DOWN);
                break;

            case LEFT:
                newX = x - 1;
                setImage(FACE_LEFT);
                break;

            case RIGHT:
                newX = x + 1;
                setImage(FACE_RIGHT);
                break;
        }

        if(!maze.getTile(newX,newY).orElse(Maze.WALL).isSolid() && !this.isDead()){
            x = newX;
            y = newY;
        }

        if(maze.getTile(newX,newY).orElse(Maze.WALL).isSolid()) {
            //do nothing
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


