package Entity;
import joc.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class entity {
    public GamePanel gp;
    UtilityTool uTool = new UtilityTool();
    public String name;
    public Rectangle attackArea=new Rectangle(0,0,0,0);
    public int worldX,worldY;
    public int speed;
    public BufferedImage up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
    public BufferedImage ad1,ad2,as1,as2;
    public String direction;
    public int damage;
    public int type; //0- player, 1-monster
    public boolean invincible=false;
    public int invincibleCount=0;
    public boolean attacking=false;

    public int spriteCounter = 0 ;
    public int spriteNumber = 1;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean coliziune=false;
    public boolean collisionOn=false;
    public int maxLife;
    public int life;
    public int actionLockCounter=0;

    public entity(GamePanel gp){
        this.gp=gp;

    }
    public void draw(Graphics2D g2, GamePanel gp){
        BufferedImage image =null;
        int screenX=worldX-gp.p1.worldX+gp.p1.screenX;
        int screenY=worldY-gp.p1.worldY+gp.p1.screenY;

        switch(direction){
            case "up":
                if(spriteNumber == 1){
                    image=up1;
                }
                if(spriteNumber == 2){
                    image=up1;
                }
                if(spriteNumber == 3){
                    image=up1;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                    image=down1;
                }
                if(spriteNumber == 2){
                    image=down1;
                }
                if(spriteNumber == 3){
                    image=down1;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image=left1;
                }
                if(spriteNumber == 2){
                    image=left1;
                }
                if(spriteNumber == 3){
                    image=left1;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image=right1;
                }
                if(spriteNumber == 2){
                    image=right1;
                }
                if(spriteNumber == 3) {
                    image = right1;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
    public void damageReaction()
    {

    }
    public void update(){


            coliziune = false;
            gp.cCheck.checkTile(this);
            gp.cCheck.checkObject(this, true);
            gp.cCheck.checkEntity(this,gp.monsters);
            boolean contactPlayer = gp.cCheck.checkPlayer(this);

            if(this.type == 1 && contactPlayer == true){
                if(gp.p1.invincible == false){
                    gp.p1.life -= damage;
                    gp.p1.invincible=true;
                }
            }

            if(coliziune == false){
                switch (direction){
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

            spriteCounter++;
            if (spriteCounter > 3) {
                spriteNumber++;
                if (spriteNumber == 3)
                    spriteNumber = 0;
                spriteCounter = 0;
            }
        }

}
