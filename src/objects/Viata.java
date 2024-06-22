package objects;

import joc.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Viata extends SuperObject{
    GamePanel gp;
    public Viata(GamePanel gp){
        this.gp= gp;
        name="viata";
        try{
            File f=new File("src\\resurse\\viata_plina.png");
            image= ImageIO.read(f);
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            f=new File("src\\resurse\\viata_jum.png");
            image2= ImageIO.read(f);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            f=new File("src\\resurse\\empty_life.png");
            image3= ImageIO.read(f);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
