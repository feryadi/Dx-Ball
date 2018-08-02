package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;


public class Shape extends JFrame {


    static public int[][] map;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    final static double speed = 0.250;

    static int screenHeight = screenSize.height;
    static int screenWidth = screenSize.width;
    static double x = speed;
    static double y = -speed;
    private static int playerHealth = 2;

    static Vector velocity = new Vector(x, y);


    /**
     * left wall uses 2.5% of the width of the screen
     * right wall uses 4% of the height of the screen
     */


    static ControlStick myJoystick = new ControlStick(((Shape.screenWidth) - ((Shape.screenWidth) * 15 / 100)) / 2, Shape.screenHeight * 93 / 100, screenWidth * 15 / 100, (Shape.screenHeight) * 5 / 200, false, false, false);
    private static Ball myFirstBall = new Ball(630, 600, 4, x, y, true, true);
    private static Wall myLeftWall = new Wall(0, ((Shape.screenHeight) * 4 / 100), (Shape.screenWidth) * 5 / (2 * 100), Shape.screenHeight);
    private static Wall myTopWall = new Wall(0, 0, Shape.screenWidth, ((Shape.screenHeight) * 6 / 100));
    private static Wall myRightWall = new Wall((Shape.screenWidth) - (Shape.screenWidth) * 5 / (2 * 100), 0, (Shape.screenWidth) * 5 / (2 * 100), Shape.screenHeight);
    private static Weapon myLeftWeapon = new Weapon(myJoystick.getX() + 10, myJoystick.getY() - 10, 7, 15, true, false, false);
    private static Weapon myRightWeapon = new Weapon(myJoystick.getX() + myJoystick.getWidth() - 20, myJoystick.getY() - 10, 7, 15, true, false, false);
    private static Wall defenceWall = new Wall(0, (Shape.screenHeight) * 95 / 100, (Shape.screenWidth), 10);

    public Shape(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        addMouseMotionListener((new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                myJoystick.setX(e.getX() - (myJoystick.getWidth() / 2));
                if (myJoystick.getX() < myRightWall.getWidth()) {
                    myJoystick.setX(myRightWall.getWidth());
                }
                if (myJoystick.getX() + myJoystick.getWidth() > myRightWall.getX()) {
                    myJoystick.setX(screenWidth - myRightWall.getWidth() - myJoystick.getWidth());
                }
                myLeftWeapon.setX(myJoystick.getX() + Shape.myJoystick.getWidth() / 10);
                myRightWeapon.setX(myJoystick.getX() + myJoystick.getWidth() - Shape.myJoystick.getWidth() / 10 - myRightWeapon.getWidth());

            }

        }));
        {

        }
        addMouseListener((new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Ammo myLeftAmmo = new Ammo(myLeftWeapon.getX(), myLeftWeapon.getY(), 4, 0, -5, true);
                        Ammo myRightAmmo = new Ammo(myRightWeapon.getX(), myRightWeapon.getY(), 4, 0, -5, true);
                        Ammo.ammoList.add(myLeftAmmo);
                        Ammo.ammoList.add(myRightAmmo);


                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }));


        addKeyListener(new KeyListener() {


            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }


        });

        setVisible(true);
        Brick.placeBricks(25, 20);
        Brick.brickList.add(myLeftWall);
        Brick.brickList.add(myRightWall);
        Brick.brickList.add(myTopWall);
        Ball.ballList.add(myFirstBall);
        if (myJoystick.isDefence()) {
            Brick.brickList.add(defenceWall);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {


            @Override
            public void run() {
                repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 16);

        Timer timer2 = new Timer();
        TimerTask task2 = new TimerTask() {

            @Override
            public void run() {
                for (Ball ball : Ball.ballList) {
                    ball.velocity();
                }
                for (Ammo ammo : Ammo.ammoList) {
                    ammo.velocity(0, -0.125);
                }
                for (PowerUp powerUp : PowerUp.powerUpList) {
                    powerUp.velocity(0, 0.125);
                }
            }
        };
        timer.scheduleAtFixedRate(task2, 0, 1);

    }

    public void paint(Graphics g) {

        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        Ball.drawCircleByCenter(graphics, myFirstBall.getX(), myFirstBall.getY(), myFirstBall.getRadius());
        for (Ammo ammo : Ammo.ammoList) {
            Ball.drawCircleByCenter(graphics, ammo.getX(), ammo.getY(), ammo.getRadius());
        }
        myJoystick.draw(graphics);
        if (myJoystick.isWeapon()) {
            myLeftWeapon.draw(graphics);
            myRightWeapon.draw(graphics);
        }
        if(myJoystick.isDefence()){
            defenceWall.draw(graphics);
        }


        for (Brick brick : Brick.brickList) {
            brick.draw(graphics);
        }
        for (PowerUp powerUp : PowerUp.powerUpList) {
            powerUp.draw(graphics);
        }


        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage, null, 0, 0);
    }


    public static int getPlayerHealth() {
        return playerHealth;
    }

    public static void setPlayerHealth(int playerHealth) {
        Shape.playerHealth = playerHealth;
    }
}

