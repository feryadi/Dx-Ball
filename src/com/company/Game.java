package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;

public class Game extends JFrame {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    static int screenHeight = screenSize.height;
    static int screenWidth = screenSize.width;
    private static int playerHealth = 2;

    /**
     * left wall uses 2.5% of the width of the screen
     * right wall uses 4% of the height of the screen
     */

    public static List<GameObject> gameObjectList = new ArrayList<>();
    public static List<GameObject> waitList = new ArrayList<>();

    public static ControlStick myJoystick = new ControlStick(((Game.screenWidth) - ((Game.screenWidth) * 15 / 100)) / 2, Game.screenHeight * 93 / 100, screenWidth * 15 / 100, (Game.screenHeight) * 5 / 200, false, false);

    public static Wall defenceWall;

    public Game(int width, int height) {
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
           /*     if (myJoystick.getX() < myRightWall.getWidth()) {
                    myJoystick.setX(myRightWall.getWidth());
                }
                if (myJoystick.getX() + myJoystick.getWidth() > myRightWall.getX()) {
                    myJoystick.setX(screenWidth - myRightWall.getWidth() - myJoystick.getWidth());
                }*/
            }
        }));
        {

        }
        addMouseListener((new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Ammo myLeftAmmo = new Ammo(myJoystick.getX() - 20, myJoystick.getY(), 4, 0, -0.1, true);
                    Ammo myRightAmmo = new Ammo(myJoystick.getX() - 10, myJoystick.getY(), 4, 0, -0.1, true);
                    addGameObject(myLeftAmmo);
                    addGameObject(myRightAmmo);
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
        addGameObject(myJoystick);
        addGameObject(new Ball(630, 600, 4, 0.250, 0.250, true, true));
        addGameObject(new Wall(0, ((Game.screenHeight) * 4 / 100), (Game.screenWidth) * 5 / (2 * 100), Game.screenHeight));
        addGameObject(new Wall(0, 0, Game.screenWidth, ((Game.screenHeight) * 6 / 100)));
        addGameObject(new Wall((Game.screenWidth) - (Game.screenWidth) * 5 / (2 * 100), 0, (Game.screenWidth) * 5 / (2 * 100), Game.screenHeight));
        defenceWall = new Wall(0, (Game.screenHeight) * 95 / 100, (Game.screenWidth), 10);
        defenceWall.setEnabled(false);
        addGameObject(defenceWall);


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
                for (GameObject gameObject : gameObjectList) {
                    gameObject.update();
                }

                for (int i = 0; i < gameObjectList.size() - 1; i++) {
                    for (int j = i; j < gameObjectList.size(); j++) {
                        CollisionManager.onCollision(gameObjectList.get(i), gameObjectList.get(j));
                    }
                }

                gameObjectList = new ArrayList<>(gameObjectList);
                gameObjectList.addAll(waitList);
                waitList.clear();
            }
        };
        timer2.scheduleAtFixedRate(task2, 0, 1);

    }

    public void paint(Graphics g) {

        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        gameObjectList.forEach(gameObject -> gameObject.draw(graphics));

        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage, null, 0, 0);
    }

    public static int getPlayerHealth() {
        return playerHealth;
    }

    public static void setPlayerHealth(int playerHealth) {
        Game.playerHealth = playerHealth;
    }

    public static void addGameObject(GameObject gameObject) {
        waitList.add(gameObject);
    }

}

