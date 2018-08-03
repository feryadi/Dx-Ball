package com.company;

public class CollisionManager {

    public static void onCollision(GameObject o1, GameObject o2) {

        if (o1 instanceof Brick && o2 instanceof Ball) {
            ballBrickCollision((Ball) o2, (Brick) o1);
            return;
        }

        if (o1 instanceof Ball && o2 instanceof Brick) {
            ballBrickCollision((Ball) o1, (Brick) o2);
            return;
        }


        if (o1 instanceof Ball && o2 instanceof ControlStick) {
            ballControlStickCollission((Ball) o1, (ControlStick) o2);
            return;
        }

        if (o1 instanceof ControlStick && o2 instanceof Ball) {
            ballControlStickCollission((Ball) o2, (ControlStick) o1);
            return;
        }


        if (o1 instanceof Ammo && o2 instanceof Brick) {
            ammoBrickCollision((Ammo) o1, (Brick) o2);
            return;
        }

        if (o1 instanceof Brick && o2 instanceof Ammo) {
            ammoBrickCollision((Ammo) o2, (Brick) o1);
            return;
        }


        if (o1 instanceof PowerUp && o2 instanceof ControlStick) {
            powerUpControlStickCollision((PowerUp) o1, (ControlStick) o2);
            return;
        }

        if (o1 instanceof ControlStick && o2 instanceof PowerUp) {
            powerUpControlStickCollision((PowerUp) o2, (ControlStick) o1);
            return;
        }


        if (o1 instanceof PowerUp && o2 instanceof Wall) {
            powerUpWallCollision((PowerUp) o1, (Wall) o2);
            return;
        }

        if (o1 instanceof Wall && o2 instanceof PowerUp) {
            powerUpWallCollision((PowerUp) o2, (Wall) o1);
            return;
        }
    }

    private static void powerUpWallCollision(PowerUp powerUp, Wall wall) {
        if (powerUp.getX() - wall.getX() <= wall.getWidth() && Math.abs(powerUp.getY() - wall.getY()) < 0.1) {
            powerUp.setX(0);
            powerUp.setY(0);
            powerUp.setMobile(false);
        }
    }

    private static void ballControlStickCollission(Ball ball, ControlStick controlStick) {
        double changeFactor = 180.0 / controlStick.getWidth();
        double vectorLength = Math.sqrt(ball.getVelY() * ball.getVelY() + ball.getVelX() * ball.getVelX());

        if (ball.getX() >= controlStick.getX()) {
            if (ball.getX() - controlStick.getX() <= controlStick.getWidth() && Math.abs(ball.getY() + ball.getRadius() - controlStick.getY()) < 0.1) {
                ball.setVelX(vectorLength * Math.cos(Math.toRadians(((ball.getX() - controlStick.getX()) * changeFactor) - 180)));
                ball.setVelY(vectorLength * Math.sin(Math.toRadians(((ball.getX() - controlStick.getX()) * changeFactor) - 180)));
            }
        }
    }

    private static void ballBrickCollision(Ball ball, Brick brick) {

        if (brick.isDestroyed()) {
            return;
        }
        if (!brick.isEnabled()) {
            return;
        }

        if (ball.getX() >= brick.getX()) {
            if (ball.getX() - brick.getX() <= brick.getWidth() && Math.abs(ball.getY() - ball.getRadius() - brick.getY() - brick.getHeight()) < 0.1) {
                if (!ball.isPiercer()) {
                    brick.setHealth(brick.getHealth() - 1);
                    ball.setVelY(-ball.getVelY());

                }
                if (ball.isPiercer()) {
                    brick.setHealth(0);
                    if (brick instanceof Wall) {
                        ball.setVelY(-ball.getVelY());
                    }
                }

                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                    ball.powerUpCaller(brick);
                }
            }
            if (ball.getX() - brick.getX() <= brick.getWidth() && Math.abs(ball.getY() + ball.getRadius() - brick.getY()) < 0.1) {
                if (!ball.isPiercer()) {
                    brick.setHealth(brick.getHealth() - 1);
                    ball.setVelY(-ball.getVelY());

                }
                if (ball.isPiercer()) {
                    brick.setHealth(0);
                    if (brick instanceof Wall) {
                        ball.setVelY(-ball.getVelY());
                    }
                }

                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                    ball.powerUpCaller(brick);
                }
            }

        }
        if ((ball.getY() >= brick.getY())) {
            if (ball.getY() - brick.getY() <= brick.getHeight() && Math.abs(ball.getX() + ball.getRadius() - brick.getX()) < 0.1) {
                if (!ball.isPiercer()) {
                    brick.setHealth(brick.getHealth() - 1);
                    ball.setVelX(-ball.getVelX());
                }
                if (ball.isPiercer()) {
                    brick.setHealth(0);
                    if (brick instanceof Wall) {
                        ball.setVelX(-ball.getVelX());
                    }
                }

                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                    ball.powerUpCaller(brick);
                }
            }
            if (ball.getY() - brick.getY() <= brick.getHeight() && Math.abs(ball.getX() - ball.getRadius() - brick.getX() - brick.getWidth()) < 0.1) {
                if (!ball.isPiercer()) {
                    brick.setHealth(brick.getHealth() - 1);
                    ball.setVelX(-ball.getVelX());
                }
                if (ball.isPiercer()) {
                    brick.setHealth(0);
                    if (brick instanceof Wall) {
                        ball.setVelX(-ball.getVelX());
                    }
                }

                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                    ball.powerUpCaller(brick);
                }
            }
        }

    }

    private static void ammoBrickCollision(Ammo ammo, Brick brick) {
        if (brick.isDestroyed()) {
            return;
        }

        if (ammo.getX() >= brick.getX()) {
            if (ammo.getX() - brick.getX() <= brick.getWidth() && Math.abs(ammo.getY() - ammo.getRadius() - brick.getY() - brick.getHeight()) < 0.1) {
                brick.setHealth(brick.getHealth() - 1);
                ammo.setMobile(false);
                ammo.setX(0);
                ammo.setY(0);
                brick.setHealth(brick.getHealth() - 1);
                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                }
            }
            if (ammo.getX() - brick.getX() <= brick.getWidth() && Math.abs(ammo.getY() + ammo.getRadius() - brick.getY()) < 0.1) {

                brick.setHealth(brick.getHealth() - 1);
                ammo.setMobile(false);
                ammo.setX(0);
                ammo.setY(0);
                brick.setHealth(brick.getHealth() - 1);
                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                }

            }

        }
        if ((ammo.getY() >= brick.getY())) {
            if (ammo.getY() - brick.getY() <= brick.getHeight() && Math.abs(ammo.getX() + ammo.getRadius() - brick.getX()) < 0.1) {
                brick.setHealth(brick.getHealth() - 1);
                ammo.setMobile(false);
                ammo.setX(0);
                ammo.setY(0);
                brick.setHealth(brick.getHealth() - 1);
                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                }

            }
            if (ammo.getY() - brick.getY() <= brick.getHeight() && Math.abs(ammo.getX() - ammo.getRadius() - brick.getX() - brick.getWidth()) < 0.1) {
                brick.setHealth(brick.getHealth() - 1);
                ammo.setMobile(false);
                ammo.setX(0);
                ammo.setY(0);
                brick.setHealth(brick.getHealth() - 1);
                if (brick.getHealth() == 0) {
                    brick.setDestroyed(true);
                }


            }
        }


    }

    private static void powerUpControlStickCollision(PowerUp powerUp, ControlStick controlStick) {
        if (controlStick.getX() - powerUp.getX() <= powerUp.getWidth() && controlStick.getY() - powerUp.getY() < 0.1) {
            powerUp.setX(0);
            powerUp.setY(0);
            powerUp.setMobile(false);
            PowerUp.powerUpConstructor(powerUp.powerUpNumber);
        }
    }
}
