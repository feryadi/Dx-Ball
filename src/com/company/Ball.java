package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.Shape.myJoystick;
import static com.company.Shape.screenHeight;
import static com.company.Shape.velocity;


public class Ball extends Ellipse2D.Double {
    private double x;
    private double y;
    private double radius;
    private double velX;
    private double velY;
    private boolean mobile;
    private boolean piercer;
    private Random random = new Random();

    static final List<Ball> ballList = new ArrayList<>();


    public Ball(double x, double y, double radius, double velX, double velY, boolean mobile, boolean piercer) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velX = velX;
        this.velY = velY;
        this.mobile = mobile;
        this.piercer = piercer;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
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

    public boolean isPiercer() {
        return piercer;
    }

    public void setPiercer(boolean piercer) {
        this.piercer = piercer;
    }


    static void drawCircleByCenter(Graphics g, double x, double y, double radius) {
        g.setColor(Color.GRAY);
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }


    public void velocity() {
        double VelX = velocity.x;
        double VelY = velocity.y;

        setX(getX() + velocity.x);
        setY(getY() + velocity.y);


        if (getY() >= Shape.screenHeight + getRadius()) {
            for (Ball ball : Ball.ballList) {
                    ball.setX(0);
                    ball.setY(0);
                    velocity.x = 0;
                    velocity.y = 0;
                    ball.setMobile(false);
                    if (!isMobile()) {
                        System.out.println("Game Over");
                    }


            }


        }


        double changeFactor = 180.0 / myJoystick.getWidth();
        double vectorLength = Math.sqrt(velocity.y * velocity.y + velocity.x * velocity.x);

        if (getX() >= Shape.myJoystick.getX()) {
            if (getX() - Shape.myJoystick.getX() <= myJoystick.getWidth() && Math.abs(getY() + getRadius() - Shape.myJoystick.getY()) < 0.1) {
                velocity.x = vectorLength * Math.cos(Math.toRadians(((getX() - Shape.myJoystick.getX()) * changeFactor) - 180));
                velocity.y = vectorLength * Math.sin(Math.toRadians(((getX() - Shape.myJoystick.getX()) * changeFactor) - 180));

            }
        }

        for (Brick brick : Brick.brickList) {
            if (brick.isDestroyed()) {
                continue;
            }

            if (getX() >= brick.getX()) {
                if (getX() - brick.getX() <= brick.getWidth() && Math.abs(getY() - getRadius() - brick.getY() - brick.getHeight()) < 0.1) {
                    if (!isPiercer()) {
                        brick.setHealth(brick.getHealth() - 1);
                        velocity.y = -VelY;

                    }
                    if (isPiercer()) {
                        brick.setHealth(0);
                        if (brick instanceof Wall) {
                            velocity.y = -VelY;
                        }
                    }

                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
                if (getX() - brick.getX() <= brick.getWidth() && Math.abs(getY() + getRadius() - brick.getY()) < 0.1) {
                    if (!isPiercer()) {
                        brick.setHealth(brick.getHealth() - 1);
                        velocity.y = -VelY;

                    }
                    if (isPiercer()) {
                        brick.setHealth(0);
                        if (brick instanceof Wall) {
                            velocity.y = -VelY;
                        }
                    }

                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }

            }
            if ((getY() >= brick.getY())) {
                if (getY() - brick.getY() <= brick.getHeight() && Math.abs(getX() + getRadius() - brick.getX()) < 0.1) {
                    if (!isPiercer()) {
                        brick.setHealth(brick.getHealth() - 1);
                        velocity.x = -VelX;

                    }
                    if (isPiercer()) {
                        brick.setHealth(0);
                        if (brick instanceof Wall) {
                            velocity.x = -VelX;
                        }
                    }

                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
                if (getY() - brick.getY() <= brick.getHeight() && Math.abs(getX() - getRadius() - brick.getX() - brick.getWidth()) < 0.1) {
                    if (!isPiercer()) {
                        brick.setHealth(brick.getHealth() - 1);
                        velocity.x = -VelX;
                    }
                    if (isPiercer()) {
                        brick.setHealth(0);
                        if (brick instanceof Wall) {
                            velocity.x = -VelX;
                        }
                    }

                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
            }


        }

    }

    /**
     * Randoming to decide if powerUp will be called and which one to call.
     */

    private void powerUpCaller(Brick brick) {
        int chanceToGetPowerUp = random.nextInt(6) + 1;
        if (chanceToGetPowerUp == 1) {
            PowerUp powerUp = new PowerUp(brick.getX() + brick.getWidth() / 4, brick.getY() - 30, 25, 25, 0, 0.125, true);
            PowerUp.powerUpNumber = 13;
            PowerUp.powerUpList.add(powerUp);

        }
    }


}

