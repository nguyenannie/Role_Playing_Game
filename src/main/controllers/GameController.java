package main.controllers;

import java.util.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import main.models.*;

public class GameController {

    private static final int INITIAL_MONSTER_AMOUNT = 20;
    private Hero hero;
    private List<Monster> monsterList;
    private int monsterAmount;
    private Monster keyMonster;
    private BossMonster boss;

    private Random random = new Random();

    private Maze maze;

    public GameController() {
        monsterAmount = INITIAL_MONSTER_AMOUNT;
        maze = new Maze();
        maze.generate();
        hero = new Hero();
        initGame();
    }

    private void initGame() {
        monsterList = new ArrayList<>();
        int[] monsPos;

        for(int i = 0; i < monsterAmount - 2; i ++) {
            monsPos = findEmptyTile();
            monsterList.add(new Monster(monsPos[0], monsPos[1],hero.level, false));
        }

        monsPos = findEmptyTile();
        keyMonster = new Monster(monsPos[0], monsPos[1],hero.level,true);

        monsPos = findEmptyTile();
        boss = new BossMonster(monsPos[0], monsPos[1],hero.level);

        monsterList.add(keyMonster);
        monsterList.add(boss);
    }

    private int[] findEmptyTile() {
        int[] pos = new int[2];

        do {
            pos[0] = random.nextInt(Maze.MAZE_WIDTH);
            pos[1] = random.nextInt(Maze.MAZE_HEIGHT);
        } while(maze.getTile(pos[0], pos[1]).orElse(Maze.WALL).isSolid());

        return pos;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster(int i) {
        return monsterList.get(i);
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public Maze getMaze() {
        return maze;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        boolean heroMoved = false;

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                hero.move(maze, Hero.Directions.LEFT);
                heroMoved = true;
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                hero.move(maze, Hero.Directions.RIGHT);
                heroMoved = true;
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                hero.move(maze, Hero.Directions.UP);
                heroMoved = true;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                hero.move(maze, Hero.Directions.DOWN);
                heroMoved = true;
                break;

            case KeyEvent.VK_SPACE:
                for(int i = 0; i < monsterList.size(); i ++) {
                    if (monsterList.get(i).x == hero.x && monsterList.get(i).y == hero.y) {
                        hero.attack(monsterList.get(i));
                        if(monsterList.get(i).isDead()){
                            hero.getStronger();
                            monsterList.remove(i);
                        } else {
                            monsterList.get(i).attack(hero);
                        }
                    }
                }
                break;
            default:
        }

        if(heroMoved) {
            for (Monster monster : monsterList) {
                monster.takeTurn(maze);
            }
        }

        if((keyMonster.isDead() && boss.isDead()) || monsterList.isEmpty()) {
            nextLevel();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                break;
            default:
        }
    }

    private void nextLevel() {
        Random r = new Random();
        maze.generate();
        hero.updateLevel();
        hero.levelUp();
        hero.initCharacter();
        monsterAmount = r.nextInt(5)+ INITIAL_MONSTER_AMOUNT;
        initGame();
    }

}

