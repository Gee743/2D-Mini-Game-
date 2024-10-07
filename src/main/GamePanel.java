package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreeenCol = 16;
    final int maxScreeenRow = 12;
    final int screenWidth = tileSize * maxScreeenCol; // 768 px
    final int screenHeight = tileSize * maxScreeenRow; // 576 px

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        while(gameThread != null) {

           System.out.println("The Game Loop is running");
        }
    }
}
