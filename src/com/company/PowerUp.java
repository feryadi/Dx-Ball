package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PowerUp {


    private double x;
    private double y;
    private double width;
    private double height;
    private double velX;
    private double velY;
    private boolean mobile;
    static public int powerUpNumber;

    static final List<PowerUp> powerUpList = new ArrayList<>();


    public PowerUp(double x, double y, double width, double height, double velX, double velY, boolean mobile) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    public static void powerUpConstructor(int powerUpNumber) {

        switch (powerUpNumber) {
            case 1:
                /**
                 *  making ball twice as big while maintaining maximum size
                 */
                for (Ball ball : Ball.ballList) {
                    if (ball.getRadius() < 10) {
                        ball.setRadius(ball.getRadius() * 2);
                    }
                }

                break;
            case 2:
                for (Ball ball : Ball.ballList) {
                    if (ball.getRadius() > 5) {
                        ball.setRadius(ball.getRadius() / 2);
                    }
                }

                break;
            case 3:
                if (Shape.myJoystick.getWidth() > Shape.screenWidth * 15 / 100) {
                    Shape.myJoystick.setWidth(Shape.myJoystick.getWidth() / 2);
                }
                break;
            case 4:
                if (Shape.myJoystick.getWidth() < Shape.screenWidth * 30 / 100) {
                    Shape.myJoystick.setWidth(Shape.myJoystick.getWidth() * 2);
                }
                break;
            case 5:
                for (Brick brick : Brick.brickList) {
                    brick.setHealth(1);
                }
                break;
            case 6:
                Shape.setPlayerHealth(Shape.getPlayerHealth() + 1);
                break;
            case 7:
                Shape.setPlayerHealth(Shape.getPlayerHealth() - 1);
                break;
            case 8:
                Shape.myJoystick.setWeapon(true);
                break;
            case 9:
                Shape.myJoystick.setGrasp(true);
                break;
            case 10:
                //create 1 more ball and make them bounce as if there is a mirror in the middle of them.

                break;
            case 11:
                //create 2 more ball and make them move with triangle shape.
                break;
            case 12:
                //create total of 8 balls out of 1 ball moving symmetrically away from each other

                break;
            case 13:
                Shape.myJoystick.setDefence(true);
                break;
            case 14:

                break;
            case 15:

                break;
        }
    }
}
