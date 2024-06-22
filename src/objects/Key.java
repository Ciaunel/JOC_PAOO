package objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Key extends SuperObject {

    public Key(){
        name = "key";
        try{
            File f= new File("src\\resurse\\cheie.png");
            image= ImageIO.read(f);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
