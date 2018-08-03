package com.company;

import java.awt.*;

public abstract class GameObject {
    private double x;
    private double y;
    private double velX;
    private double velY;
    private boolean isMobile;
    private boolean isEnabled = true;

    public GameObject(double x, double y, double velX, double velY, boolean isMobile) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.isMobile = isMobile;
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
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public void update() {
        if (!isMobile || !isEnabled()) {
            return;
        }

        x += velX;
        y += velY;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public abstract void draw(Graphics graphics);
}
