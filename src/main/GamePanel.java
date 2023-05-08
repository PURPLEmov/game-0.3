package main;

import entity.Player;
import main.KeyHandler;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 4;
    public final int tileSize = originalTileSize * scale;//16*4=64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//64*16(maxScreenCol)=1024
    public final int screenHeight = tileSize * maxScreenRow;//64*12(maxScreenRow)=768

    //   FPS    FPS    FPS    FPS     FPS     FPS    FPS   FPS   FPS  FPS
    int FPS = 60;

    TileManager tileM=new TileManager(this);
    KeyHandler keyH=new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecher=new CollisionChecker(this);
    Player player=new Player(this, keyH);

    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval=1000000000/FPS;//1/60
        double nextDrawTime = System.nanoTime()+drawInterval;

        while (gameThread !=null){
            //System.out.println("mere");
            //update info / draw
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime-System.nanoTime();
                remainingTime=remainingTime/1000000;

                if (remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}