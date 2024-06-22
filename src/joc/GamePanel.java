package joc;
import Tiles.*;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.io.IOException;

import Entity.*;
import objects.SuperObject;

public class GamePanel extends JPanel implements Runnable{ //GamePanel devine o subclasa a clasei JPanel care exista deja in PACHETUL JAVAX.SWING
    //Screen settings
    final int originalTileSize=16;//dimensiunea tile-urile fixata 16x16, default pentru caracter
    final int scale=3;// aceasta variabila va fi utilizata pentru a scala dimensiunile caracterelor

    public final int tileSize=originalTileSize*scale; //se obtine 48X48
    public final int maxScreenCol =16; //16 coloane
    public final int maxScreenRow=12; //12randuri => 12x16 <=> 4:3
    public final int screenWidth =tileSize*maxScreenCol; //768 pixel
    public final int screenHeight=tileSize*maxScreenRow; //576 pixels
    //FPS = frame per seconds
    public final int maxWorldCol=50;
    public final int maxWorldRow=25;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    public int maxMapCount=3;
    public int mapNum=0;
    public int FinishState=4;
    int FPS=60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH=new KeyHandler(this);
    Thread gameThread; //thread = firul de executie corespunzator jocului, ruleaza programul pana cand il vom opri noi
    //necesita implementarea unei clase Runnable, ce este de facpt o interfata ce contine thread-urile si ce contin ele
    public Coliziuni collision=new Coliziuni(this);
    public Player p1= Player.getInstance(this, keyH);
    public AssetSetter aSetter= new AssetSetter(this);
    public UI ui = new UI(this);
    public SuperObject []obj=new SuperObject[10];
    public entity []monsters=new entity[20];
    public int gameState;
    public final int titleState =0;
    public final int playState=1;
    public final int pauseState=2;
    public final int GameOver=3;

    public EventHandler eHand=new EventHandler(this);
    public Coliziuni cCheck=new Coliziuni(this);


    public GamePanel() throws IOException {
        //constructor
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);//recunoaste inputurile
        this.setFocusable(true);//aceasta ajuta ca programul sa fie focusat sa primeasca intrarea
    }
    public void setUpGame(){
        aSetter.setObject();
        aSetter.setMonster();
        gameState=titleState;
    }
    public void StartGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        double drawInterval = (double) 1000000000 /FPS;
        double delta=0; //metoda delta/accumulator
        long lastTime=System.nanoTime();
        long currentTime;

        while(gameThread!=null){
            currentTime=System.nanoTime();
            delta += (currentTime -lastTime)/drawInterval;
            lastTime=currentTime;

            if(delta>=1)
            {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update(){
        if(gameState ==playState)
        { p1.update();
            for(int i=0;i< monsters.length;i++)
            {
                if(monsters[i]!=null){
                    monsters[i].update();
                }

            }
        }
    }
    public void paintComponent(Graphics g){ //aceasta metoda exista deja in JPanel
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g; //convertim la grafica 2D ce avem, aceasta este o sublcasa a clasei Graphics
        if(gameState==titleState){
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            for(int i=0;i<monsters.length;i++){
                if(monsters[i]!=null){
                    monsters[i].draw(g2,this);
                }
            }

            p1.draw(g2);
            ui.draw(g2);
            g2.dispose(); //
        }
    }
}
