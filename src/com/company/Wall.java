package com.company;

public class Wall extends Brick {

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height, 0);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}