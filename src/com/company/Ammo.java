package com.company;

import java.util.ArrayList;
import java.util.List;

public class Ammo {
    private double x;
    private double y;
    private double radius;
    private double velX;
    private double velY;
    private boolean mobile;
    static final List<Ammo> ammoList = new ArrayList<>();

    public Ammo(double x, double y, double radius, double velX, double velY, boolean mobile) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velX = velX;
        this.velY = velY;
        this.mobile = mobile;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }
    public void velocity(double VelX, double VelY) {
        if (isMobile()) {
            setX(getX() + VelX);
            setY(getY() + VelY);
        }

        for (Ammo ammo : Ammo.ammoList) {
            for (Brick brick : Brick.brickList) {
                if (brick.isDestroyed()) {
                    continue;
                }
                if (ammo.getX() - brick.getX() <= brick.getWidth() && ammo.getY() - brick.getY() < 0.1) {
                    ammo.setMobile(false);
                    ammo.setX(0);
                    ammo.setY(0);
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                    }


                }
            }

        }


    }

}
