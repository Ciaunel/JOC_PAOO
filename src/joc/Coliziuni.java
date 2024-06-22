package joc;

import Entity.entity;

public class Coliziuni {
    GamePanel gp;
    public Coliziuni(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(entity e1){
        int entityLeftWorldX=e1.worldX+e1.solidArea.x;
        int entityRightWorldx=e1.worldX+e1.solidArea.x+e1.solidArea.width;
        int entityTopWorldY=e1.worldY+e1.solidArea.y;
        int entityBottomWorldY=e1.worldY+e1.solidArea.y+e1.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldx/gp.tileSize;
        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;

        switch(e1.direction){
            case "up":
                entityTopRow=(entityTopWorldY-e1.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.mapNum][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.mapNum][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].coliziune==true || gp.tileM.tile[tileNum2].coliziune==true)
                    e1.coliziune=true;
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+e1.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.mapNum][entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[gp.mapNum][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].coliziune==true || gp.tileM.tile[tileNum2].coliziune==true)
                    e1.coliziune=true;
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-e1.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.mapNum][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.mapNum][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].coliziune==true || gp.tileM.tile[tileNum2].coliziune==true)
                    e1.coliziune=true;
                break;
            case "right":
                entityRightCol=(entityRightWorldx+e1.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.mapNum][entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.mapNum][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].coliziune==true || gp.tileM.tile[tileNum2].coliziune==true)
                    e1.coliziune=true;
                break;

        }
    }
    public int checkObject(entity e1,boolean player) {
        int index = 999;
        //daca ia obiectul retine indexul si l returneaza
        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {
                //incadram obiectul intr-un dreptunghi
                e1.solidArea.x = e1.worldX + e1.solidArea.x;
                e1.solidArea.y = e1.worldY + e1.solidArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (e1.direction) {
                    case "up":
                        e1.solidArea.y -= e1.speed;
                        if (e1.solidArea.intersects(gp.obj[i].solidArea)) {
                            System.out.println("up collision!");
                            if(gp.obj[i].coliziune==true){
                                e1.coliziune=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "down":
                        e1.solidArea.y += e1.speed;
                        if (e1.solidArea.intersects(gp.obj[i].solidArea)) {
                            System.out.println("down collision!");
                            if(gp.obj[i].coliziune==true){
                                e1.coliziune=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "left":
                        e1.solidArea.x -= e1.speed;
                        if (e1.solidArea.intersects(gp.obj[i].solidArea)) {
                            System.out.println("left collision!");
                            if(gp.obj[i].coliziune==true){
                                e1.coliziune=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "right":
                        e1.solidArea.x += e1.speed;
                        if (e1.solidArea.intersects(gp.obj[i].solidArea)) {
                            System.out.println("right collision!");
                            if(gp.obj[i].coliziune==true){
                                e1.coliziune=true;
                            }
                            if(player==true){
                                index=i;
                            }
                            break;
                        }
                }
                e1.solidArea.x = e1.solidAreaDefaultX;
                e1.solidArea.y = e1.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;

    }
    public int checkEntity(entity e1, entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                e1.solidArea.x = e1.worldX + e1.solidArea.x;
                e1.solidArea.y = e1.worldY + e1.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (e1.direction) {
                    case "up":
                        e1.solidArea.y -= e1.speed;
                        break;
                    case "down":
                        e1.solidArea.y += e1.speed;
                        break;
                    case "left":
                        e1.solidArea.x -= e1.speed;
                        break;
                    case "right":
                        e1.solidArea.x += e1.speed;
                        break;
                    default:
                        break;
                }
                if (e1.solidArea.intersects(target[i].solidArea) && target[i] != e1) {
                    e1.coliziune = true;
                    index = i;
                }

                e1.solidArea.x = e1.solidAreaDefaultX;
                e1.solidArea.y = e1.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(entity entity){

        boolean contactPlayer = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.p1.solidArea.x = gp.p1.worldX + gp.p1.solidArea.x;
        gp.p1.solidArea.y = gp.p1.worldY + gp.p1.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.p1.solidArea)) {
                    entity.coliziune = true;
                    contactPlayer = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.p1.solidArea)) {
                    entity.coliziune= true;
                    contactPlayer = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.p1.solidArea)) {
                    entity.coliziune= true;
                    contactPlayer = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.p1.solidArea)) {
                    entity.coliziune = true;
                    contactPlayer = true;
                }
                break;
            default:
                break;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.p1.solidArea.x = gp.p1.solidAreaDefaultX;
        gp.p1.solidArea.y = gp.p1.solidAreaDefaultY;

        return contactPlayer;
    }

}
