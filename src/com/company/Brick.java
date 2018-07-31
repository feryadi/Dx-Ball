package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    private int health;
    private boolean destroyed;
    private final int maxHealth;

    static final List<Brick> brickList = new ArrayList<>();

    public Brick(int x, int y, int width, int height, int health) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.maxHealth = health;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void draw(Graphics g) {
        if (isDestroyed())
            return;
        int color = (int) (255 - 255 * getHealthRatio());
        g.setColor(new Color(color, color, color));
        g.fillRect(x, y, width, height);
    }

    public boolean isDestroyed() {

        return destroyed;
    }

    public void setDestroyed(boolean val) {
        destroyed = val;
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
                brickList.add(new Brick(125 + (i * 45), (100 + (j * 20)), 45, 20, 1));
            }
        }
    }

}