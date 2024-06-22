package objects;

import joc.GamePanel;
import joc.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image,image2,image3;
    public String name;
    public boolean coliziune = false;
    public int worldX, worldY;
    public Rectangle solidArea=new Rectangle(0,0,64,64);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    UtilityTool uTool =new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.p1.worldX + gp.p1.screenX; // screenX e pozitia pe ecran
        int screenY = worldY - gp.p1.worldY + gp.p1.screenY;

        if (worldX + gp.tileSize > (gp.p1.worldX - gp.p1.screenX) && worldX - gp.tileSize < (gp.p1.worldX + gp.p1.screenX) && worldY + gp.tileSize > (gp.p1.worldY - gp.p1.screenY) && worldY - gp.tileSize < (gp.p1.worldY + gp.p1.screenY)) {
            //in acest if se
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}