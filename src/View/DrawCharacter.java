package View;

import Model.Character;

import java.awt.*;

import static Model.Character.step;

public class DrawCharacter implements Drawable {
    private Character character;

    void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(character.getImage(), character.getX() * step, character.getY() * step, null);
    }
}
