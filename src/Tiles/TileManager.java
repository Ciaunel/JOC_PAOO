package Tiles;
import joc.*;
import Entity.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    public TileManager(GamePanel gp){
        this.gp=gp;
        tile = new Tile[16];
        mapTileNum=new int[gp.maxMapCount][gp.maxWorldCol][gp.maxWorldRow];//aici vom incarca matricea noastra

        getTileImage();
        loadMap("src\\maps\\map_nivel1.txt",0);
        loadMap("src\\maps\\map_lvl2.txt",1);
        loadMap("src\\maps\\map_lvl3.txt",2);
    }
    public void getTileImage(){
       try{
            tile[0]=new Tile();
            File f= new File("src\\Tiles\\perete1.jpg");
            tile[0].image= ImageIO.read(f);
            f= new File("src\\Tiles\\perete2.jpg");
            tile[1]=new Tile();
            tile[1].image=ImageIO.read(f);
            tile[1].coliziune=true;
            f= new File("src\\Tiles\\perete3.jpg");
            tile[2]=new Tile();
            tile[2].image=ImageIO.read(f);
            f= new File("src\\Tiles\\perete4.jpg");
            tile[3]=new Tile();
            tile[3].image=ImageIO.read(f);
            tile[3].coliziune=true;
            f= new File("src\\Tiles\\perete5.jpg");
            tile[4]=new Tile();
            tile[4].image=ImageIO.read(f);
            tile[4].coliziune=true;
            f= new File("src\\Tiles\\perete_apa.png");
            tile[5]=new Tile();
            tile[5].image=ImageIO.read(f);
            f= new File("src\\Tiles\\perete_ochi.png");
            tile[6]=new Tile();
            tile[6].image=ImageIO.read(f);
            f= new File("src\\Tiles\\obstacol.jpg");
            tile[7]=new Tile();
            tile[7].image=ImageIO.read(f);
            f= new File("src\\Tiles\\perete6.jpg");
            tile[8]=new Tile();
            tile[8].image=ImageIO.read(f);
            tile[8].coliziune=true;

            f= new File("src\\Tiles\\perete1.jpg");
            tile[9]=new Tile();
            tile[9].image=ImageIO.read(f);
            tile[9].coliziune=true;

            f=new File("src\\Tiles\\butoi.png");
            tile[10]=new Tile();
            tile[10].image=ImageIO.read(f);
            tile[10].coliziune=true;

            f=new File("src\\Tiles\\canal.png");
            tile[11]=new Tile();
            tile[11].image=ImageIO.read(f);

            f=new File("src\\Tiles\\oale.png");
            tile[12]=new Tile();
            tile[12].image=ImageIO.read(f);
            tile[12].coliziune=true;

           f=new File("src\\Tiles\\torta.png");
           tile[13]=new Tile();
           tile[13].image=ImageIO.read(f);
           tile[13].coliziune=true;

           f=new File("src\\Tiles\\geam.png");
           tile[14]=new Tile();
           tile[14].image=ImageIO.read(f);
           tile[14].coliziune=true;

           f=new File("src\\Tiles\\box.png");
           tile[15]=new Tile();
           tile[15].image=ImageIO.read(f);
           tile[15].coliziune=true;


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String map,int index){
        try{
            File f=new File(map);
            Scanner scanner=new Scanner(f);
            int col=0,row=0;
            while(col<gp.maxWorldCol&&row<gp.maxWorldRow){
                mapTileNum[index][col][row]=scanner.nextInt();
                col++;
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;

        while(worldCol< gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum=mapTileNum[gp.mapNum][worldCol][worldRow];
            int worldX= worldCol*gp.tileSize; //worldX e pozitia pe mapa
            int worldY= worldRow*gp.tileSize;
            int screenX=worldX-gp.p1.worldX +gp.p1.screenX; // screenX e pozitia pe ecran
            int screenY=worldY -gp.p1.worldY +gp.p1.screenY;

            if (worldX +gp.tileSize>(gp.p1.worldX -gp.p1.screenX) && worldX-gp.tileSize<(gp.p1.worldX+ gp.p1.screenX) && worldY+gp.tileSize>(gp.p1.worldY -gp.p1.screenY) &&
                    worldY-gp.tileSize<(gp.p1.worldY + gp.p1.screenY)) {
                //in acest if se
                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol=0;
                worldRow++;

            }
        }
    }
}
