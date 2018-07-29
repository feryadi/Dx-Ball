package com.company;


import java.awt.*;

public class ControlStick {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean weapon;
    private boolean grasp;
    private boolean defence;


    public ControlStick(int x, int y, int width, int height, boolean weapon, boolean grasp, boolean defence) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.weapon = weapon;
        this.grasp = grasp;
        this.defence = defence;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
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

    public boolean isDefence() {
        return defence;
    }

    public void setDefence(boolean defence) {
        this.defence = defence;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }


}
