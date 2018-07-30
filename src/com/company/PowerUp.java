package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.Shape.myJoystick;
import static com.company.Shape.velocity;

public class PowerUp {


    private int x;
    private int y;
    private int width;
    private int height;
    private boolean mobile;

    static final List<PowerUp> powerUpList = new ArrayList<>();


    public PowerUp(int x, int y, int width, int height, boolean mobile) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mobile = mobile;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public void velocity() {
        double x = velocity.x;
        double y = velocity.y;

        setX(getX() + (int) velocity.x);
        setY(getY() + (int) velocity.y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
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
            case 4:if (Shape.myJoystick.getWidth() < Shape.screenWidth* 30 / 100) {
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
                for (Ball ball : Ball.ballList) {
                    ball.getX();
                    ball.getY();
                }
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
                for (Ball ball : Ball.ballList) {
                    ball.velocity();  // 2 kat hız
                }
                break;
            case 15:

                break;
        }
    }
}
