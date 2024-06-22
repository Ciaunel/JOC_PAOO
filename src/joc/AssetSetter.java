package joc;

import Entity.Demon;
import Entity.Furnica;
import Entity.Gelato;
import objects.Door;
import objects.Key;
import objects.Obstacol;
import objects.Sandwich;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        gp.obj[0]=new Key();//24
        gp.obj[0].worldX=2*gp.tileSize;
        gp.obj[0].worldY=23*gp.tileSize;

        gp.obj[1]=new Door();
        gp.obj[1].worldY=13*gp.tileSize;
        gp.obj[1].worldX=48*gp.tileSize;

        gp.obj[2]=new Sandwich(gp);
        gp.obj[2].worldX=4*gp.tileSize;
        gp.obj[2].worldY=5*gp.tileSize;

        gp.obj[3]=new Sandwich(gp);
        gp.obj[3].worldX=30*gp.tileSize;
        gp.obj[3].worldY=10*gp.tileSize;



    }

    public void setObject2() {
        for(int i = 0;i<gp.obj.length;i++) {
            gp.obj[i] = null;
        }
        gp.obj[0]=new Key();//24
        gp.obj[0].worldX=24*gp.tileSize;
        gp.obj[0].worldY=13*gp.tileSize;

        gp.obj[1]=new Door();
        gp.obj[1].worldY=13*gp.tileSize;
        gp.obj[1].worldX=48*gp.tileSize;

        gp.obj[2]=new Obstacol();
        gp.obj[2].worldY=9*gp.tileSize;
        gp.obj[2].worldX=40*gp.tileSize;

        gp.obj[3]=new Sandwich(gp);
        gp.obj[3].worldX=4*gp.tileSize;
        gp.obj[3].worldY=5*gp.tileSize;

        gp.obj[4]=new Sandwich(gp);
        gp.obj[4].worldX=30*gp.tileSize;
        gp.obj[4].worldY=10*gp.tileSize;
    }

    public void setMonster(){
        gp.monsters[0]=new Furnica(gp);
        gp.monsters[0].worldX=40*gp.tileSize;
        gp.monsters[0].worldY=10*gp.tileSize;

        gp.monsters[1]=new Furnica(gp);
        gp.monsters[1].worldX=38*gp.tileSize;
        gp.monsters[1].worldY=gp.tileSize*11;

        gp.monsters[2]=new Furnica(gp);
        gp.monsters[2].worldX=gp.tileSize*40;
        gp.monsters[2].worldY=gp.tileSize*6;

        gp.monsters[3]=new Furnica(gp);
        gp.monsters[3].worldX=gp.tileSize*38;
        gp.monsters[3].worldY=gp.tileSize*9;

        gp.monsters[4]=new Furnica(gp);
        gp.monsters[4].worldX=gp.tileSize*32;
        gp.monsters[4].worldY=gp.tileSize*6;

        gp.monsters[5]=new Furnica(gp);
        gp.monsters[5].worldX=gp.tileSize*32;
        gp.monsters[5].worldY=gp.tileSize*9;

        gp.monsters[6]=new Furnica(gp);
        gp.monsters[6].worldX=gp.tileSize*42;
        gp.monsters[6].worldY=gp.tileSize*6;

    }
    public void setMonster2(){
        for(int i = 0;i<gp.monsters.length;i++) {
            gp.monsters[i] = null;
        }
        gp.monsters[0]=new Gelato(gp);
        gp.monsters[0].worldX=26*gp.tileSize;
        gp.monsters[0].worldY=13*gp.tileSize;

        gp.monsters[1]=new Gelato(gp);
        gp.monsters[1].worldX=21*gp.tileSize;
        gp.monsters[1].worldY=gp.tileSize*11;

        gp.monsters[2]=new Gelato(gp);
        gp.monsters[2].worldX=gp.tileSize*20;
        gp.monsters[2].worldY=gp.tileSize*11;

        gp.monsters[3]=new Gelato(gp);
        gp.monsters[3].worldX=gp.tileSize*24;
        gp.monsters[3].worldY=gp.tileSize*10;

        gp.monsters[4]=new Gelato(gp);
        gp.monsters[4].worldX=gp.tileSize*22;
        gp.monsters[4].worldY=gp.tileSize*11;

        gp.monsters[5]=new Gelato(gp);
        gp.monsters[5].worldX=gp.tileSize*24;
        gp.monsters[5].worldY=gp.tileSize*16;

        gp.monsters[6]=new Gelato(gp);
        gp.monsters[6].worldX=gp.tileSize*28;
        gp.monsters[6].worldY=gp.tileSize*17;

        gp.monsters[7]=new Gelato(gp);
        gp.monsters[7].worldX=gp.tileSize*29;
        gp.monsters[7].worldY=gp.tileSize*11;

        gp.monsters[8]=new Gelato(gp);
        gp.monsters[8].worldX=gp.tileSize*25;
        gp.monsters[8].worldY=gp.tileSize*11;

        gp.monsters[9]=new Gelato(gp);
        gp.monsters[9].worldX=gp.tileSize*23;
        gp.monsters[9].worldY=gp.tileSize*17;
    }
    public void setMonster3(){
        for(int i = 0;i<gp.monsters.length;i++) {
            gp.monsters[i] = null;
        }
        gp.monsters[0]=new Demon(gp);
        gp.monsters[0].worldX=16*gp.tileSize;
        gp.monsters[0].worldY=10*gp.tileSize;
    }
    public void setObject3(){
        for(int i = 0;i<gp.obj.length;i++) {
            gp.obj[i] = null;
        }
        gp.obj[0]=new Obstacol();
        gp.obj[0].worldY=13*gp.tileSize;
        gp.obj[0].worldX=10*gp.tileSize;

        gp.obj[1]=new Obstacol();
        gp.obj[1].worldY=13*gp.tileSize;
        gp.obj[1].worldX=11*gp.tileSize;

        gp.obj[2]=new Obstacol();
        gp.obj[2].worldY=13*gp.tileSize;
        gp.obj[2].worldX=12*gp.tileSize;

        gp.obj[3]=new Obstacol();
        gp.obj[3].worldY=13*gp.tileSize;
        gp.obj[3].worldX=13*gp.tileSize;

        gp.obj[4]=new Obstacol();
        gp.obj[4].worldY=9*gp.tileSize;
        gp.obj[4].worldX=10*gp.tileSize;

        gp.obj[5]=new Sandwich(gp);
        gp.obj[5].worldX=15*gp.tileSize;
        gp.obj[5].worldY=12*gp.tileSize;

        gp.obj[6]=new Door();
        gp.obj[6].worldY=12*gp.tileSize;
        gp.obj[6].worldX=33*gp.tileSize;


    }
}
