package com.company;

import java.awt.*;
import java.util.Random;


public class PowerUp extends GameObject {
    public static Random random = new Random();
    private double width;
    private double height;
    private boolean mobile;
    int powerUpNumber;

    public PowerUp(double x, double y, boolean mobile) {
        super(x, y, 0, 0.125, mobile);
        this.width = 25;
        this.height = 25;
        this.mobile = mobile;
        powerUpNumber = random.nextInt(15) + 1;
        //      powerUpNumber = 12;
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

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) getX(), (int) getY(), (int) width, (int) height);
    }

    public static void powerUpConstructor(int powerUpNumber) {
        switch (powerUpNumber) {
            case 1:
                // making ball twice as big while maintaining maximum size

                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        if (ball.getRadius() < 8) {
                            ball.setRadius(ball.getRadius() * 2);
                        }
                    }
                }

                break;
            case 2:

                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        if (ball.getRadius() > 4) {
                            ball.setRadius(ball.getRadius() / 2);
                        }
                    }
                }


                break;
            case 3:
                if (Game.myJoystick.getWidth() > Game.screenWidth * 15 / 200) {
                    Game.myJoystick.setWidth(Game.myJoystick.getWidth() / 5 * 4);
                }
                break;
            case 4:
                if (Game.myJoystick.getWidth() < Game.screenWidth * 20 / 100) {
                    Game.myJoystick.setWidth(Game.myJoystick.getWidth() * 5 / 4);
                }
                break;
            case 5:
                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Brick) {
                        Brick brick = (Brick) gameObject;
                        brick.setHealth(1);
                    }
                }
                break;
            case 6:
                Game.setPlayerHealth(Game.getPlayerHealth() + 1);
                break;
            case 7:
                Game.setPlayerHealth(Game.getPlayerHealth() - 1);
                break;
            case 8:
                Game.myJoystick.setWeapon(true);
                break;
            case 9:
                Game.myJoystick.setGrasp(true);
                break;
            case 10:
                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        ball.setPiercer(true);
                    }
                }

                break;
            case 11:
                //create 2 more ball and make them move with triangle shape.

                double vectorLength;


                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;

                        double x = ball.getX();
                        double y = ball.getY();
                        double velX = ball.getVelX();
                        double velY = ball.getVelY();
                        vectorLength = Math.sqrt(velX * velX + velY * velY);

                        for (int i = 0; i < 3; i++) {
                            Ball myBall = new Ball(x, y, 5, vectorLength * Math.cos(Math.toRadians(i * 120)), vectorLength * Math.sin(Math.toRadians(i * 120)), true, false);
                            Game.addGameObject(myBall);
                        }
                    }
                }

                break;
            case 12:
                //create 8 balls and make them move away from each other symmetrically

                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        double x = ball.getX();
                        double y = ball.getY();
                        double velX = ball.getVelX();
                        double velY = ball.getVelY();
                        vectorLength = Math.sqrt(velX * velX + velY * velY);

                        for (int i = 0; i < 3; i++) {
                            Ball myBall = new Ball(x, y, 5, vectorLength * Math.cos(Math.toRadians(i * 45)), vectorLength * Math.sin(Math.toRadians(i * 45)), true, false);
                            Game.addGameObject(myBall);
                        }
                    }
                }

                break;
            case 13:
                //create a wall under the paddle that will reflect incoming balls for a limited time.
                Game.defenceWall.setEnabled(true);
                break;
            case 14:

                break;
            case 15:

                break;
        }
    }

}
