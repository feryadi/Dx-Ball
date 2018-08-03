package com.company;

import java.awt.*;

public class ControlStick extends GameObject {

    private double width;
    private double height;
    private boolean weapon;
    private boolean grasp;

    public ControlStick(double x, double y, double width, double height, boolean weapon, boolean grasp) {
        super(x, y, 0, 0, false);
        this.width = width;
        this.height = height;
        this.weapon = weapon;
        this.grasp = grasp;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public void setWeapon(boolean weapon) {
        this.weapon = weapon;
    }

    public boolean isGrasp() {
        return grasp;
    }

    public void setGrasp(boolean grasp) {
        this.grasp = grasp;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int) getX(), (int) getY(), (int) width, (int) height);
    }

}
