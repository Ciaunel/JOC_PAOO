package joc;

import objects.Key;
import objects.SuperObject;
import objects.Viata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font ink_free_40;
    BufferedImage keyImage;
    BufferedImage img;
    BufferedImage viata_plina,viata_jum,viata_goala;
    public boolean messageOn=false;
    public String message= "";
    int messageCounter =0;
    public int  commandNum=0;
    public int titleScreenState=0;

    public UI(GamePanel gp){
        this.gp=gp;
        ink_free_40=new Font("Ink Free",Font.BOLD,40);
        Key key=new Key();
        keyImage= key.image;
        SuperObject heart =new Viata(gp);
        viata_plina=heart.image;
        viata_jum=heart.image2;
        viata_goala=heart.image3;

    }
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2) {
        g2.setFont(ink_free_40);
        g2.setColor(Color.white);
        if(gp.p1.hasKey>0) {
            g2.drawImage(keyImage, gp.tileSize * 13, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("= " + gp.p1.hasKey, 690, 65);
        }

        switch(gp.mapNum){
            case 0: g2.drawString("LVL 1",345,65); break;
            case 1: g2.drawString("LVL 2",345,65); break;
            case 2: g2.drawString("LVL 3",345,65); break;
        }


        if(messageOn ==true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,gp.tileSize*6,gp.tileSize*5);
            messageCounter++;

            if(messageCounter>60){
                messageCounter=0;
                messageOn=false;
            }
        }
        this.g2=g2;
        g2.setFont(ink_free_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        else if(gp.gameState==gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        else if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        else if(gp.gameState==gp.GameOver){
            drawGameOverScreen();
        }
        else if(gp.gameState==gp.FinishState){
            drawFinishState();
        }

    }

    public void drawPlayerLife(){
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
        while(i<gp.p1.maxLife/2){
            g2.drawImage(viata_goala,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;
        while(i<gp.p1.life){
            g2.drawImage(viata_jum,x,y,null);
            i++;
            if(i<gp.p1.life){
                g2.drawImage(viata_plina,x,y,null);

            }
            i++;
            x+=gp.tileSize;
        }
        if(gp.p1.life==0){
            gp.gameState= gp.GameOver;
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState==0){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
            g2.setColor(Color.gray);
            String text="START";
            int x=getXforCenteredText(text)-gp.tileSize;
            int y=gp.screenHeight/2;

            try {
                File f = new File("src\\resurse\\open_page.png");
                img = ImageIO.read(f);
                g2.drawImage(img, 0, 0,gp.screenWidth,gp.screenHeight, null);
            }
            catch(IOException e){
                e.printStackTrace();
            }

            g2.drawString(text,x,y);
            g2.setColor(Color.black);
            g2.drawString(text,x+1,y+1);
            //g2.drawOval(x-50,y-70,gp.screenWidth/3,gp.screenHeight/5);
            if(commandNum==0){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text="QUIT";
            x=getXforCenteredText(text)-gp.tileSize;
            y=y+gp.tileSize*2;
            g2.setColor(Color.gray);
            g2.drawString(text,x,y);
            g2.setColor(Color.black);
            g2.drawString(text,x+1,y+1);
            //g2.drawOval(x-50,y-70,gp.screenWidth/5,gp.screenHeight/7);
            if(commandNum==1){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text="LOAD GAME";
            x=getXforCenteredText(text)-gp.tileSize;
            y=y+gp.tileSize*2;
            g2.setColor(Color.gray);
            g2.drawString(text,x,y);
            g2.setColor(Color.black);
            g2.drawString(text,x+1,y+1);
            if(commandNum==2){
                g2.drawString(">",x-gp.tileSize,y);
            }

        }
    }
        public void drawPauseScreen(){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
            String text= "PAUSE";
            int x=getXforCenteredText("PAUSE");
            int y=gp.screenHeight/2;

            g2.setColor(new Color(0,0,0,110));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
        }
        public void drawGameOverScreen(){
        BufferedImage image;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text="GAME OVER";
        int x=getXforCenteredText(text);
        int y=gp.screenHeight/2;

        g2.setColor(new Color(0,0,0,110));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setColor(Color.white);
        g2.drawString(text,x,y-gp.tileSize*2);
        text="HOME";
        x=getXforCenteredText(text);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text="QUIT";
       // x=getXforCenteredText(text);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        g2.drawString(text,x+1,y+gp.tileSize) ;
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y+gp.tileSize);
            }

        try {
            File f = new File("src\\resurse\\GAME_OVER.png");
            image = ImageIO.read(f);
            g2.drawImage(image, x, y+gp.tileSize*2,gp.screenWidth/4,gp.screenHeight/3, null);
        }catch(IOException e){
            e.printStackTrace();
        }


        }
        public void drawFinishState(){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
            String text ="Congratulations!";
            int x=getXforCenteredText(text);
            int y=gp.screenHeight/2-gp.tileSize;
            g2.drawString(text,x,y);

            g2.setColor(new Color(0,0,0,110));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            g2.setColor(Color.white);
            g2.drawString(text,x,y);

            text="YOU ESCAPE!";
            x=x+gp.tileSize;
            y=y+gp.tileSize*2;
            g2.drawString(text,x,y);

            text="QUIT";
            x=x+gp.tileSize*2;
            y=y+gp.tileSize*2;
            g2.drawString(text,x,y);
            if(commandNum==0){
                g2.drawString(">",x+1,y+1);
            }
        }

    public int getXforCenteredText(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x= gp.screenWidth/2 -length/2;
        return x;
    }
}
