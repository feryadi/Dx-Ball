package com.company;

public class Vector {


    double x;
    double y;



    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }


    Vector add (Vector vector) {

        return new Vector(this.x + vector.x, this.y + vector.y);
    }


    Vector multiply(int multiplier) {

        return new Vector(this.x * multiplier, this.y * multiplier);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
