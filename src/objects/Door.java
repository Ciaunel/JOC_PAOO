package objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Door extends SuperObject{
    public Door(){
        name = "door";
        try{
            File f= new File("src\\resurse\\usa.png");
            image= ImageIO.read(f);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        coliziune=true;
    }
}
