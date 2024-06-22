package joc;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static joc.DbConnection.insert;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    Graphics2D g2;
    public boolean upPressed, downPressed,leftPressed,rightPressed,spacePressed, enter;
    @Override
    public void keyTyped(KeyEvent e) {

    }
    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();//rreturneaza un intreg asociat cu "cheia" evenimentului
//HOME SCREEN
        if(gp.gameState==gp.titleState){
            if(code==KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=2;
                }
            }
            if(code==KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum>2){
                    gp.ui.commandNum=0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum==0){
                    gp.p1.setDefaultValues();
                    gp.gameState=gp.playState;

                }
                if(gp.ui.commandNum==1){
                    System.exit(0);
                }
                if(gp.ui.commandNum==2){
                    //load game
                    int []v=null;
                    v=DbConnection.readAllData();

                    gp.mapNum=v[0];
                    gp.p1.worldX=v[1];
                    gp.p1.worldY=v[2];
                    gp.p1.life=v[3];
                    gp.gameState = gp.playState;
                }
            }
        }
//Play State
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if(code==KeyEvent.VK_ESCAPE){
                DbConnection.deleteRow();
                DbConnection.insert(gp.mapNum,gp.p1.worldX,gp.p1.worldY,gp.p1.life);
                System.exit(0);
            }

            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                }

            }
    }else if(gp.gameState==gp.pauseState){
            if(code==KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState==gp.GameOver){
            if(code==KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0)
                {gp.ui.commandNum=1;
                }
            }
            if(code==KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum>1)
                {
                    gp.ui.commandNum=0;
                }
            }
            if(code== KeyEvent.VK_ENTER){
                switch(gp.ui.commandNum){
                    case 0:
                        gp.gameState= gp.titleState;
                        break;
                    case 1:
                        System.exit(0);
                        break;
                }
            }

        }
        else if(gp.gameState== gp.FinishState){

            if(code==KeyEvent.VK_ENTER){
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_SPACE){
            spacePressed=false;
        }
        if(code==KeyEvent.VK_LEFT){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_RIGHT){
            rightPressed=false;
        }
        if(code==KeyEvent.VK_UP){
            upPressed=false;
        }
        if(code==KeyEvent.VK_DOWN){
            downPressed=false;
        }
    }
}
