package main.views;

import main.models.Character;

import java.awt.*;

import static main.models.Character.STEP;

public class DrawCharacter implements Drawable {

    private Character character;

    void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(character.getImage(), character.getX() * STEP, character.getY() * STEP, null);
    }

}
