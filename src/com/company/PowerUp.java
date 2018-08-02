package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.Shape.myJoystick;
import static com.company.Shape.screenHeight;

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

        for (PowerUp powerUp : PowerUp.powerUpList) {
            if (myJoystick.getX() - powerUp.getX() <= powerUp.getWidth() && myJoystick.getY() - powerUp.getY() < 0.1) {
                powerUp.setX(0);
                powerUp.setY(0);
                powerUp.setMobile(false);
                PowerUp.powerUpConstructor(PowerUp.powerUpNumber);
            }
            if (powerUp.getY() > screenHeight) {
                powerUp.setX(0);
                powerUp.setY(0);
                powerUp.setMobile(false);
            }
        }


    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    public static void powerUpConstructor(int powerUpNumber) {

        switch (powerUpNumber) {
            case 1:

                 // making ball twice as big while maintaining maximum size

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
                if (Shape.myJoystick.getWidth() > Shape.screenWidth * 15 / 200) {
                    Shape.myJoystick.setWidth(Shape.myJoystick.getWidth() / 5*4);
                }
                break;
            case 4:
                if (Shape.myJoystick.getWidth() < Shape.screenWidth * 20 / 100) {
                    Shape.myJoystick.setWidth(Shape.myJoystick.getWidth() * 5/4);
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
                for (Ball ball : Ball.ballList) {
                    ball.setPiercer(true);
                }
                break;
            case 11:
                //create 2 more ball and make them move with triangle shape.
                double vectorLength;
                for (Ball ball : Ball.ballList) {
                    double x = ball.getX();
                    double y = ball.getY();
                    double velX = ball.getVelX();
                    double velY = ball.getVelY();
                    vectorLength = Math.sqrt(velX * velX + velY * velY);

                    for (int i = 0; i < 3; i++) {
                        Ball myBall = new Ball(x, y, 5, vectorLength * Math.cos(Math.toRadians(i * 120)), vectorLength * Math.sin(Math.toRadians(i * 120)), true, false);
                        Ball.ballList.add(myBall);
                    }
                }
                break;
            case 12:
                //create 8 balls and make them move away from each other symmetrically
                for (Ball ball : Ball.ballList) {
                    double x = ball.getX();
                    double y = ball.getY();
                    double velX = ball.getVelX();
                    double velY = ball.getVelY();
                    vectorLength = Math.sqrt(velX * velX + velY * velY);

                    for (int i = 0; i < 8; i++) {
                        Ball myBall =  new Ball(x, y, 5, vectorLength * Math.cos(Math.toRadians(i * 45)), vectorLength * Math.sin(Math.toRadians(i * 45)), true, false);
                        Ball.ballList.add(myBall);
                    }
                }
                break;
            case 13:
                //create a wall under the paddle that will reflect incoming balls for a limited time.
                Shape.myJoystick.setDefence(true);
                break;
            case 14:

                break;
            case 15:

                break;
        }
    }
}
