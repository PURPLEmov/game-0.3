package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp=gp;
        tile=new Tile[10];
        mapTileNum=new int[gp.maxScreenCol][gp.maxScreenRow];


        getTileImage();
        loadMap("/maps/map1.txt");
    }
        public void getTileImage() {
            try    {
                tile[0]=new Tile();
                tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/soft_stone.png")) ;

                tile[1]=new Tile();
                tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hard_stone.png")) ;
                tile[1].colision=true;

            }catch (IOException e)  {
                e.printStackTrace();
            }
        }
        public void loadMap(String filePath){
        try{
            InputStream inputStream =getClass().getResourceAsStream(filePath);
            BufferedReader br =new BufferedReader(new InputStreamReader(inputStream));

            int col=0;
            int row=0;

            while(col<gp.maxScreenCol && row< gp.maxScreenRow){
                String line=br.readLine();
                while(col<gp.maxScreenCol){
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col== gp.maxScreenCol){
                    col=0;
                    row++;
                }
            }
            br.close();


        }catch(Exception e){
            e.printStackTrace();
        }
        }
        public void draw(Graphics2D g2){
            int col=0;
            int row=0;
            int x=0;
            int y=0;
            while (col<gp.maxScreenCol && row<gp.maxScreenRow){

                int tileNum=mapTileNum[col][row];

                g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
                col++;
                x+=gp.tileSize;

                if (col==gp.maxScreenCol){
                    col=0;
                    x=0;
                    row++;
                    y+= gp.tileSize;

                }
            }
    }
}

