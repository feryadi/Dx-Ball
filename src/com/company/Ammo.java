package com.company;

import java.awt.*;

public class Ammo extends GameObject {
    private double radius;

    public Ammo(double x, double y, double radius, double velX, double velY, boolean mobile) {
        super(x, y, velX, velY, mobile);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillOval((int) (getX() - radius), (int) (getY() - radius), (int) (2 * radius), (int) (2 * radius));
    }

}
