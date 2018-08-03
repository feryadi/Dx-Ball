package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Brick extends GameObject {
    private double width;
    private double height;
    private int health;
    private boolean destroyed;
    private final int maxHealth;

    public Brick(double x, double y, double width, double height, int health) {
        super(x, y, 0, 0, false);
        this.width = width;
        this.height = height;
        this.health = health;
        this.maxHealth = health;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void draw(Graphics g) {
        if (isDestroyed())
            return;
        if (!isEnabled())
            return;
        int color = (int) (255 - 255 * getHealthRatio());
        g.setColor(new Color(color, color, color));
        g.fillRect((int) getX(), (int) getY(), (int) width, (int) height);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean setDestroyed(boolean val) {
        destroyed = val;
        return val;
    }

    private double getHealthRatio() {
        if (maxHealth == 0)
            return 1;
        else
            return health * 1.0 / maxHealth;
    }

    static void placeBricks(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Game.addGameObject(new Brick(125 + (i * 45), (100 + (j * 20)), 45, 20, 2));
            }
        }
    }

}