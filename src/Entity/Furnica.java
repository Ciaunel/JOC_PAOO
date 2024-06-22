package Entity;

import joc.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Furnica extends entity {
    public Furnica(GamePanel gp) {
        super(gp);
        //this.gp=gp;
        name = "furnica";
        speed = 3;
        maxLife = 100;
        type=1;
        life = maxLife;
        direction = "down";
        damage = 1;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        try {
            File f = new File("src\\NPC\\inamic1.png");
            up1 = ImageIO.read(f);
            up1 = uTool.scaleImage(up1, gp.tileSize, gp.tileSize);
            f = new File("src\\NPC\\inamic1_spate.png");
            down1 = ImageIO.read(f);


            down1 = uTool.scaleImage(down1, gp.tileSize, gp.tileSize);
            f = new File("src\\NPC\\inamic1_parte.png");
            right1 = ImageIO.read(f);
            right1 = uTool.scaleImage(right1, gp.tileSize, gp.tileSize);
            f = new File("src\\NPC\\inamic1_parte2.png");
            left1 = ImageIO.read(f);
            left1 = uTool.scaleImage(left1, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        actionLockCounter++;
        coliziune = false;
        gp.cCheck.checkTile(this);
        int indexM = gp.cCheck.checkEntity(gp.p1, gp.monsters);

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

        coliziune = false;
        gp.cCheck.checkTile(this);
        gp.cCheck.checkObject(this, false);
        gp.cCheck.checkEntity(this, gp.monsters);
        boolean contactPlayer = gp.cCheck.checkPlayer(this);

         if(this.type == 1 && contactPlayer == true){
             if(gp.p1.invincible == false){
                gp.p1.life -= this.damage;
                gp.p1.invincible=true;
            }
          }

        if (coliziune == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
    }
    public void damageReaction(){
        actionLockCounter=0;
        direction=gp.p1.direction;
    }
}
