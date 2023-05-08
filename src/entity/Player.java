package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp, KeyHandler keyH) throws IOException {
        this.gp=gp;
        this.keyH=keyH;

        solidArea=new Rectangle(0,0,64,64);

        setDefaultValues();
        getPlayerImage();
    }
public void setDefaultValues() throws IOException {
    x = 100;
    y = 100;
    speed = 6;
    direction="down";

}
    public void getPlayerImage(){
        try{
            System.out.println("Image loading started");
            File f1 = new File("./res/player/stang.png");
            File f2 = new File("./res/player/stang.png");
            File f3 = new File("./res/player/drept.png");
            File f4 = new File("./res/player/drept.png");
            File f5 = new File("./res/player/stang.png");
            File f6 = new File("./res/player/stang.png");
            File f7 = new File("./res/player/drept.png");
            File f8 = new File("./res/player/drept.png");
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);// God bless David Mrlik
            System.out.println("Image loading ended");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update () {
        if (keyH.upPressed) {
            direction="up";
        } else if (keyH.downPressed) {
            direction="down";
        } else if (keyH.leftPressed) {
            direction="left";
        } else if (keyH.rightPressed) {
            direction="right";
        }
        boolean collisionOn= false;
        gp.cChecher.checkTile(this);

    if(collision==false){
        switch (direction){
            case "left":
                if (keyH.leftPressed) {
                    x -= speed;
                }
                break;
            case "right":
                if (keyH.rightPressed) {
                    x += speed;
                }
                break;
            case "up":
                if (keyH.upPressed) {
                    y -= speed;
                }
                break;
            case "down":
                if (keyH.downPressed) {
                    y += speed;
                }
                break;
        }
    }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.magenta);
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image=null;

        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
        }
}