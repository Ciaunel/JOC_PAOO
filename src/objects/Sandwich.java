package objects;

import joc.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Sandwich extends SuperObject{
    public Sandwich(GamePanel gp){

        name = "sandwich";
        try{
            File f= new File("src\\resurse\\sandwich.png");
            image= ImageIO.read(f);
            image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
