package joc;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX,eventRectDefaultY;
    public EventHandler(GamePanel gp){
        this.gp=gp;
        eventRect=new Rectangle();
        eventRect.x=23;
        eventRect.y=23;
        eventRect.width=2;
        eventRect.height=2;
        eventRectDefaultX=eventRect.x;
        eventRectDefaultY=eventRect.y;
    }
    public void checkEvent(){
       // if(hit(27,16,"right")==true){
     //       damageObstacol(gp.playState);
      //  }

        if(hit(48,13,"any") == true && gp.mapNum<3){
            if(gp.mapNum == 0)
            {
                gp.mapNum = 1;
                gp.aSetter.setObject2();
                gp.aSetter.setMonster2();
            }
            else if(gp.mapNum==1){
                gp.mapNum =2;
                gp.aSetter.setObject3();
                gp.aSetter.setMonster3();
            }


            gp.p1.worldX=gp.tileSize*1;//pozitia jucatorului pe mapa, nu pe ecran
            gp.p1.worldY=gp.tileSize*12;
        }
    }
    public boolean hit(int eventCol,int eventRow, String regDirection){
        boolean hit=false;
        gp.p1.solidArea.x=gp.p1.worldX+gp.p1.solidArea.x;
        gp.p1.solidArea.y=gp.p1.worldY+gp.p1.solidArea.y;
        eventRect.x=eventCol*gp.tileSize+eventRect.x;
        eventRect.y=eventRow*gp.tileSize+eventRect.y;
        if(gp.p1.solidArea.intersects(eventRect)){
            if(gp.p1.direction.contentEquals(regDirection)||regDirection.contentEquals("any")){
                hit=true;
            }
        }
        gp.p1.solidArea.x=gp.p1.solidAreaDefaultX;
        gp.p1.solidArea.y=gp.p1.solidAreaDefaultY;
        eventRect.x=eventRectDefaultX;
        eventRect.y=eventRectDefaultY;

        return hit;
    }
    public void damageObstacol(int gameState){
        gp.gameState=gameState;
        gp.p1.life-=1;
    }
}
