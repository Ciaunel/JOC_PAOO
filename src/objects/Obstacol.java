package objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Obstacol extends SuperObject{
    public Obstacol(){
        name = "obstacol";
        try{
            File f= new File("src\\Tiles\\obstacol2.png");
            image= ImageIO.read(f);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
