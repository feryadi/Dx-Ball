package com.company;

import java.util.Random;

public class PowerUp {


    Random random = new Random(15);
    int n = random.nextInt(10) + 1;


    public void PowerUp(int n) {


        switch (n) {
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
                Shape.myJoystick.setWidth(Shape.myJoystick.getWidth() / 2);
                break;
            case 4:
                Shape.myJoystick.setWidth(Shape.myJoystick.getWidth() * 2);
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
        }
    }
}