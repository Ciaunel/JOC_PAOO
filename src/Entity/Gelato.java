package Entity;

import joc.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Gelato extends entity{
    public Gelato(GamePanel gp){
        super(gp);
        //this.gp=gp;
        name = "gelato";
        speed = 5;
        maxLife = 120;
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
    public void getImage(){
        try{
            File f= new File("src\\NPC\\i2s.png");
            up1= ImageIO.read(f);
            f= new File("src\\NPC\\i2s1.png");
            up2=ImageIO.read(f);
            f= new File("src\\NPC\\i2s2.png");
            up3=ImageIO.read(f);
            f= new File("src\\NPC\\i2f.png");
            down1=ImageIO.read(f);
            f= new File("src\\NPC\\i2f2.png");
            down2=ImageIO.read(f);
            f= new File("src\\NPC\\i2f3.png");
            down3=ImageIO.read(f);
            f= new File("src\\NPC\\i2st.png");
            left1=ImageIO.read(f);
            f= new File("src\\NPC\\i2st1.png");
            left2=ImageIO.read(f);
            f= new File("src\\NPC\\i2st2.png");
            left3=ImageIO.read(f);
            f= new File("src\\NPC\\i2d.png");
            right1=ImageIO.read(f);
            f= new File("src\\NPC\\i2d2.png");
            right2=ImageIO.read(f);
            f= new File("src\\NPC\\i2d3.png");
            right3=ImageIO.read(f);

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
