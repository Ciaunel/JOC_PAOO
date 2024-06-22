package Entity;
import joc.*;
import objects.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends entity{
    KeyHandler keyH;

    public int screenX = 0;//aceste coordonate indic[ locul pe ecran unde vom desena jucatorul
    public int screenY=0;
    public int hasKey=0;
    public static Player instance;
    public static Player getInstance(GamePanel gp, KeyHandler keyH) throws IOException {
        if(instance==null){
            instance=new Player(gp,keyH);
        }
        return instance;
    }
    private Player(GamePanel gp, KeyHandler keyH) throws IOException {
        super(gp);

        this.keyH=keyH;
        damage=1;

        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);

        solidArea= new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;
        attackArea.width=48;
        attackArea.height=48;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        worldX=gp.tileSize*1;//pozitia jucatorului pe mapa, nu pe ecran
        worldY=gp.tileSize*12;
        speed=4;
        direction="down";
        maxLife=10;
        life=maxLife;
    }
    public void getPlayerImage() throws IOException{
        try{
            File f= new File("src\\resurse\\s1.png");
            up1=ImageIO.read(f);
            f= new File("src\\resurse\\s2.png");
            up2=ImageIO.read(f);
            f= new File("src\\resurse\\s3.png");
            up3=ImageIO.read(f);
            f= new File("src\\resurse\\f1.png");
            down1=ImageIO.read(f);
            f= new File("src\\resurse\\f2.png");
            down2=ImageIO.read(f);
            f= new File("src\\resurse\\f3.png");
            down3=ImageIO.read(f);
            f= new File("src\\resurse\\st1.png");
            left1=ImageIO.read(f);
            f= new File("src\\resurse\\st2.png");
            left2=ImageIO.read(f);
            f= new File("src\\resurse\\st3.png");
            left3=ImageIO.read(f);
            f= new File("src\\resurse\\dr1.png");
            right1=ImageIO.read(f);
            f= new File("src\\resurse\\dr2.png");
            right2=ImageIO.read(f);
            f= new File("src\\resurse\\dr3.png");
            right3=ImageIO.read(f);

        } catch(IOException e){
            e.printStackTrace();
        }

    }
    public void getPlayerAttackImage(){
        try{
            File f= new File("src\\resurse\\attack_2.png");
            ad1=ImageIO.read(f);
            //space1=uTool.scaleImage(space1,gp.tileSize,gp.tileSize);
            f=new File("src\\resurse\\attack_e.png");
            ad2=ImageIO.read(f);
            f=new File("src\\resurse\\attack_2_st.png");
            as1=ImageIO.read(f);
            f=new File("src\\resurse\\attack_e_st.png");
            as2=ImageIO.read(f);
            //space2=uTool.scaleImage(space2,gp.tileSize*14,gp.tileSize*14);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(attacking==true){
            attacking();
        }

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed==true)
        {
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed)
            {
                direction="down";
            }
            else if(keyH.leftPressed)
            {
                direction="left";
            }
            else if(keyH.rightPressed)
            {
                direction="right";
            }

            //verific[ coliziunea, adica daca poate trece de obiectul solid sau nu
            coliziune=false;
            gp.collision.checkTile(this);
            gp.eHand.checkEvent();
            int objIndex = gp.collision.checkObject(this,true);
            pickUpObject(objIndex);
            int indexM=gp.collision.checkEntity(this,gp.monsters);
            if(indexM!=999){
                if(gp.p1.invincible == false) {
                    gp.p1.life -= gp.monsters[indexM].damage;
                    gp.p1.invincible = true;
                }
                //gp.monsters[indexM]=null;
            }
            else{
                if(gp.p1.keyH.spacePressed==true){
                    attacking=true;
                }
            }
           // gp.eHand.checkEvent();
            if(coliziune==false){
                switch(direction){
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

            if(invincible == true){
                invincibleCount++;
                if(invincibleCount == 120){
                    invincibleCount = 0;
                    invincible = false;
                }
            }

            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNumber==1){
                    spriteNumber=2;
                }
                else if(spriteNumber==2){
                    spriteNumber=3;
                }
                else if(spriteNumber==3){
                    spriteNumber=1;
                }
                spriteCounter=0;
            }
        }

    }
    public void attacking(){
        spriteCounter++;

        if(spriteCounter<=5){
            spriteNumber=1;
        }
       if(spriteCounter>5&&spriteCounter<=25){
           spriteNumber=2;
       }
       int currentWorldX=worldX;
       int currentWorldY=worldY;
       int solidAreaWidth=solidArea.width;
       int solidAreaHeight=solidArea.height;

       switch(direction){
           case "up": worldY-=attackArea.height;break;
           case "down": worldY+=attackArea.height;break;
           case "left": worldX-=attackArea.width;break;
           case "right": worldX+=attackArea.width;break;
       }
       solidArea.width=attackArea.width;
       solidArea.height=attackArea.height;

       int monsterIndex=gp.cCheck.checkEntity(this,gp.monsters);
       damageMonster(monsterIndex);
       worldX=currentWorldX;
       worldY=currentWorldY;
       solidArea.width=solidAreaWidth;
       solidArea.height=solidAreaHeight;

       if(spriteCounter>25){
           spriteNumber=1;
           spriteCounter=0;
           attacking=false;
       }
    }
    public void damageMonster(int index){
        if(index!=999){
            System.out.println("Hit!");
            gp.monsters[index].life-=gp.p1.damage;
            gp.monsters[index].damageReaction();
            if(gp.monsters[index].life<=0){
                if(gp.monsters[index].name=="demon"){
                    gp.obj[7]=new Key();
                    gp.obj[7].worldX=gp.monsters[index].worldX+1;
                    gp.obj[7].worldY=gp.monsters[index].worldY+1;
                }
                gp.monsters[index] = null;
            }
        }
        else {
            System.out.println("MISS!");
        }
    }
    public void pickUpObject(int i) {
        if (i != 999) {
            String ObjectName =gp.obj[i].name;

            switch(ObjectName){
                case "key":
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "door":
                    if(hasKey>0){
                        gp.obj[i]=null;
                        hasKey--;
                        if(gp.mapNum==2){
                            gp.gameState=gp.FinishState;
                        }
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "obstacol":
                    if(gp.p1.invincible == false) {
                        gp.p1.life -= 1;
                        gp.p1.invincible = true;
                    }
                    break;
                case "sandwich":
                    if(gp.p1.life<gp.p1.maxLife-1){
                        gp.p1.life+=2;
                        gp.obj[i]=null;
                    }

            }
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image =null;
        //int tempScreenX=screenX;
        //int tempScreenY=screenY;
        switch(direction){
            case "up":
                if(attacking==false){
                if(spriteNumber == 1){image=up1;}
                if(spriteNumber == 2){image=up2;}
                if(spriteNumber == 3){image=up3;}
                }
                else {
                   // tempScreenY=screenY-gp.tileSize;
                    if(spriteNumber == 1){image=ad1;}
                    if(spriteNumber == 2){image=ad2;}
                    if(spriteNumber == 3){image=ad1;}
                }
                break;
            case "down":
                if(attacking==false) {
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                    if (spriteNumber == 3) {
                        image = down3;
                    }
                }
                else {
                    if (spriteNumber == 1) {
                        image = as1;
                    }
                    if (spriteNumber == 2) {
                        image = as2;
                    }
                    if (spriteNumber == 3) {
                        image = as1;
                    }
                }
                break;
            case "left":
                if(attacking==false) {
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                    if (spriteNumber == 3) {
                        image = left3;
                    }
                }
                else {
                    //tempScreenX=screenX-gp.tileSize;
                    if (spriteNumber == 1) {
                        image = as1;
                    }
                    if (spriteNumber == 2) {
                        image = as2;
                    }
                    if (spriteNumber == 3) {
                        image = as1;
                    }
                }
                break;
            case "right":
                if(attacking==false) {
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                    if (spriteNumber == 3) {
                        image = right3;
                    }
                }
                else{
                    if(spriteNumber == 1){image=ad1;}
                    if(spriteNumber == 2){image=ad2;}
                    if(spriteNumber == 3){image=ad1;}
                }
                break;

        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
