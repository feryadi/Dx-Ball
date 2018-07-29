package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.Shape.myJoystick;
import static com.company.Shape.velocity;


public class Ball extends Ellipse2D.Double {
    private double x;
    private double y;
    private double radius;
    private boolean mobile;
    Random random = new Random();

    static final List<Ball> ballList = new ArrayList<>();


    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }


    public Ball(double x, double y, double radius, boolean mobile) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mobile = mobile;
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

    public double getColYFromDown() {
        return getY() - getRadius() / 2;
    }

    public double getColYFromUp() {
        return getY() + getRadius() / 2;
    }

    public double getColXFromRight() {
        return getX() - getRadius() / 2;
    }

    public double getColXFromLeft() {
        return getX() + getRadius() / 2;
    }


    static void drawCircleByCenter(Graphics g, double x, double y, double radius) {
        g.setColor(Color.GRAY);
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }


    public void velocity() {
        double x = velocity.x;
        double y = velocity.y;

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
                    velocity.y = -y;
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
                if (getX() - brick.getX() <= brick.getWidth() && Math.abs(getY() + getRadius() - brick.getY()) < 0.1) {
                    velocity.y = -y;
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
            }
            if ((getY() >= brick.getY())) {
                if (getY() - brick.getY() <= brick.getHeight() && Math.abs(getX() + getRadius() - brick.getX()) < 0.1) {
                    velocity.x = -x;
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
                if (getY() - brick.getY() <= brick.getHeight() && Math.abs(getX() - getRadius() - brick.getX() - brick.getWidth()) < 0.1) {
                    velocity.x = -x;
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                        powerUpCaller(brick);
                    }
                }
            }

        }

        for (PowerUp powerUp : PowerUp.powerUpList) {
        if(getX()-powerUp.getX()<=powerUp.getWidth() && getY()-powerUp.getY()<0.1) {
            powerUp.setX(0);
            powerUp.setY(0);
            powerUp.setMobile(false);
            }

        }


        for (Ball ammo : Weapon.ammoList) {
            for (Brick brick : Brick.brickList) {
                if (brick.isDestroyed()) {
                    continue;
                }
                if (ammo.getX() - brick.getX() <= brick.getWidth() && ammo.getY() - brick.getY() < 0.1) {
                    brick.setHealth(brick.getHealth() - 1);
                    if (brick.getHealth() == 0) {
                        brick.setDestroyed(true);
                    }
                }
            }

        }
    }

    /**
     * Randoming to decide if powerUp will be called and which one to call.
     */

    private void powerUpCaller(Brick brick) {
        if (brick.getHealth() == 0) {
            brick.setDestroyed(true);
            int chanceToGetPowerUp = random.nextInt(6) + 1;
            if (chanceToGetPowerUp == 1) {
                PowerUp powerUp = new PowerUp(brick.getX(), brick.getY() + 30, 15, 15,true);
                PowerUp.powerUpList.add(powerUp);
                int powerUpVariable = random.nextInt(13) + 1;
                {
                    PowerUp.powerUpConstructor(powerUpVariable);
                }
            }
        }
    }


}





