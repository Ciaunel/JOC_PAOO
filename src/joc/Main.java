package joc;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);//nu putem modifica  dimensiunea
        window.setTitle("ADVENTURE TIME");

        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);

        window.pack();//dimensioneaza fereastra dupa preferintele setate in GamePanel

        window.setLocationRelativeTo(null);//locatia ferestrei este nespecificata, ceea ce va face sa apara centrata pe ecran
        window.setVisible(true);//facem posibila vizualizarea ferestrei

        gamePanel.setUpGame();
        gamePanel.StartGameThread();
        }
}