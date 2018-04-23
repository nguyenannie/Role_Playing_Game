package main.models;

import javax.swing.*;

public class BossMonster extends Monster {

    private static final String BOSS_IMAGE = "/Users/annie/MyGithubProjects/TkWanderer/src/resources/images/boss.gif";

    public BossMonster(int xc, int yc, int level) {
        this.level = level;
        maxHealthPoint = 2 * level * rollDice() + rollDice();
        defendPoint = (int)Math.ceil(level/2.0 * rollDice() + rollDice()/2.0);
        strikePoint = level * rollDice() + getLevel();
        healthPoint = maxHealthPoint;
        initCharacter(xc,yc);
    }

    void initCharacter(int xPos, int yPos){
        ImageIcon icon = new ImageIcon(BOSS_IMAGE);
        image = icon.getImage();
        x = xPos;
        y = yPos;
    }

}
