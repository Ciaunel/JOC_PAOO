package Entity;

import joc.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Demon extends entity{
    public Demon(GamePanel gp){
        super(gp);
        //this.gp=gp;
        name = "demon";
        speed = 10;
        maxLife = 200;
        type=1;
        life = maxLife;
        direction = "down";
        damage = 2;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage(){
        try{

            File f= new File("src\\NPC\\demon1.png");
            down1=ImageIO.read(f);
            down2=ImageIO.read(f);
            f= new File("src\\NPC\\demon2.png");
            down3=ImageIO.read(f);
            f= new File("src\\NPC\\demon3.png");
            up1=ImageIO.read(f);
            f= new File("src\\NPC\\demon4.png");
            up2=ImageIO.read(f);
            up3=up1;
            f= new File("src\\NPC\\demon5.png");
            left1=ImageIO.read(f);
            f= new File("src\\NPC\\demon6.png");
            left2=ImageIO.read(f);
            left3=left1;
            right1=left1;
            right2=left2;
            right3=left1;

        } catch(IOException e){
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
