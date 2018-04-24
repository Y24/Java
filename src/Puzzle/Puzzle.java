package Puzzle;

import java.awt.*;
import java.awt.event.*;

public class Puzzle extends Frame {
    public static void main(String[] argc){
        Puzzle MainWindow=new Puzzle();
        MainWindow.setSize(new Dimension(600,600));
        MainWindow.setTitle("15-square Puzzle");
        MainWindow.setVisible(true);
    }
    public Puzzle(){
        addKeyListener(new KeyAdapter() {
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(new GridLayout(4,4));
        setFont(new Font("SansSerif",Font.BOLD,24));
        for(int i=1;i<16;i++)
                add(new Button("" + i));
        add(new Button());
            }
    }

