package Puzzle;

import java.awt.*;

public class Puzzle extends Frame {
    public static void main(String[] argc){
        Puzzle MainWindow=new Puzzle();
    }
    public Puzzle(){
        addKeyListener();
        addMouseListener();
        addWindowListener();
    }
}
