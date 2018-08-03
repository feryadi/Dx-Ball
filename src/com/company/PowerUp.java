package com.company;

import java.awt.*;
import java.util.Random;


public class PowerUp extends GameObject {
    private double width;
    private double height;
    private boolean mobile;
    PowerUpType powerUpNumber;

    public PowerUp(double x, double y, boolean mobile) {
        super(x, y, 0, 0.125, mobile);
        this.width = 25;
        this.height = 25;
        this.mobile = mobile;
        powerUpNumber = PowerUpType.getPowerUp();
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

    public static void powerUpConstructor(PowerUpType powerUpType) {
        switch (powerUpType) {
            case LARGE_BALL:
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
            case SMALL_BALL:
                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        if (ball.getRadius() > 4) {
                            ball.setRadius(ball.getRadius() / 2);
                        }
                    }
                }


                break;
            case SMALL_PADLE:
                if (Game.myJoystick.getWidth() > Game.screenWidth * 15 / 200) {
                    Game.myJoystick.setWidth(Game.myJoystick.getWidth() / 5 * 4);
                }
                break;
            case LARGE_PADLE:
                if (Game.myJoystick.getWidth() < Game.screenWidth * 20 / 100) {
                    Game.myJoystick.setWidth(Game.myJoystick.getWidth() * 5 / 4);
                }
                break;
            case BRICK_CURSE:
                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Brick) {
                        Brick brick = (Brick) gameObject;
                        brick.setHealth(1);
                    }
                }
                break;
            case LIFE_UP:
                Game.setPlayerHealth(Game.getPlayerHealth() + 1);
                break;
            case LIFE_DOWN:
                Game.setPlayerHealth(Game.getPlayerHealth() - 1);
                break;
            case WEAPON:
                Game.myJoystick.setWeapon(true);
                break;
            case GRASP:
                Game.myJoystick.setGrasp(true);
                break;
            case PIERCING_BALL:
                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        ball.setPiercer(true);
                    }
                }

                break;
            case THREE_BALL:
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
            case EIGHT_BALL:
                //create 8 balls and make them move away from each other symmetrically

                for (GameObject gameObject : Game.gameObjectList) {
                    if (gameObject instanceof Ball) {
                        Ball ball = (Ball) gameObject;
                        double x = ball.getX();
                        double y = ball.getY();
                        double velX = ball.getVelX();
                        double velY = ball.getVelY();
                        vectorLength = Math.sqrt(velX * velX + velY * velY);

                        for (int i = 0; i < 8; i++) {
                            Ball myBall = new Ball(x, y, 5, vectorLength * Math.cos(Math.toRadians(i * 45)), vectorLength * Math.sin(Math.toRadians(i * 45)), true, false);
                            Game.addGameObject(myBall);
                        }
                    }
                }

                break;
            case DEFENCE:
                //create a wall under the paddle that will reflect incoming balls for a limited time.
                Game.defenceWall.setEnabled(true);
                break;
        }
    }


    enum PowerUpType {
        LARGE_BALL(1),
        SMALL_BALL(1),
        PIERCING_BALL(1),
        DEFENCE(1),
        WEAPON(1),
        THREE_BALL(1),
        EIGHT_BALL(100),
        SMALL_PADLE(1),
        LARGE_PADLE(1),
        LIFE_UP(1),
        LIFE_DOWN(1),
        GRASP(1),
        BRICK_CURSE(1);

        private static double totalWeight = -1;
        private final double weight;

        PowerUpType(double weight) {
            this.weight = weight;
        }

        private static void setTotalWeight() {
            if (totalWeight != -1)
                return;

            totalWeight = 0;

            for (PowerUpType value : PowerUpType.values()) {
                totalWeight += value.weight;
            }
        }

        static PowerUpType getPowerUp() {
            setTotalWeight();

            Random random = new Random();
            double v = random.nextDouble() * totalWeight;

            double a = 0;
            for (PowerUpType value : PowerUpType.values()) {
                a += value.weight;
                if (a > v) {
                    return value;
                }
            }
            return LARGE_BALL;
        }
    }


}
