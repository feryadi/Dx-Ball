package com.company;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject {
    private double radius;
    private boolean piercer;
    private Random random = new Random();

    public Ball(double x, double y, double radius, double velX, double velY, boolean mobile, boolean piercer) {
        super(x, y, velX, velY, mobile);
        this.radius = radius;
        this.piercer = piercer;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isPiercer() {
        return piercer;
    }

    public void setPiercer(boolean piercer) {
        this.piercer = piercer;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillOval((int) (getX() - radius), (int) (getY() - radius), (int) (2 * radius), (int) (2 * radius));
    }

    /**
     * Randoming to decide if powerUp will be called and which one to call.
     */
    public void powerUpCaller(Brick brick) {
        int chanceToGetPowerUp = random.nextInt(6) + 1;
        if (chanceToGetPowerUp == 1) {
            PowerUp powerUp = new PowerUp(brick.getX() + brick.getWidth() / 4, brick.getY() - 30, true);
            Game.addGameObject(powerUp);
        }
    }


}

