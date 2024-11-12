package main;

import entity.Player;
import object.SuperObject;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {


    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreeenCol = 16;
    public final int maxScreeenRow = 12;
    public final int screenWidth = tileSize * maxScreeenCol; // 768 px
    public final int screenHeight = tileSize * maxScreeenRow; // 576 px

    //WORLD SETTTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreeenCol;
    public final int worldHeight = tileSize * maxScreeenRow;


    //FPS
    int FPS =60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void setUpGame() {
        aSetter.setObject();
    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override

    //Other approachable method
  /*  public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            long currentTime = System.nanoTime();
            long currentTime2 = System.currentTimeMillis();

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    } */

    public void run () {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime ) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
            }

        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //TILE
        tileM.draw(g2);

        //OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();
    }
}
