package Puzzle;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle extends Frame implements ActionListener {
    private final static int n = 4;
    private final static int Sum = n * n;
    private static int flag = 15;
    private Button[] ButtonArr = new Button[Sum];
    private String[] ButtonName = Random(Sum);

    /*
     * @para len represents the length of the solution array
     *
     * function you input a length of an array,this method will return an array
     * of number from 1 to len with unsorted.
     */
    private String[] Random(int len) {
        Integer Arr[] = new Integer[len - 1];
        String S[] = new String[len];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len - 1; i++)
            list.add(i + 1);
        Collections.shuffle(list);
        list.toArray(Arr);
        for (int i = 0; i < len - 1; i++)
            S[i] = Arr[i].toString();
        S[len - 1] = " ";
        return S;
    }

    public static void main(String[] argc) {
        Puzzle MainWindow = new Puzzle();
        MainWindow.setSize(new Dimension(600, 600));
        MainWindow.setTitle("15-square Puzzle");
        MainWindow.setVisible(true);
    }

    private Puzzle() {
        //  addKeyListener(new KeyAdapter() {
        //});
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(new GridLayout(n, n));
        setFont(new Font("SansSerif", Font.BOLD, 24));
        for (int i = 0; i < Sum; i++) {
            ButtonArr[i] = (Button) add(new Button(ButtonName[i]));
            ButtonArr[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int Pressed = 0; Pressed < Sum; Pressed++)
            if (e.getSource() == ButtonArr[Pressed]) {
                if (Pressed / n == flag / n) {
                    if (Pressed - flag == 1 || flag - Pressed == 1) {
                        ButtonArr[flag].setLabel(ButtonArr[Pressed].getLabel());
                        ButtonArr[Pressed].setLabel(" ");
                        flag = Pressed;
                    }
                } else if (Pressed % n == flag % n) {
                    if (Pressed - flag == n || flag - Pressed == n) {
                        ButtonArr[flag].setLabel(ButtonArr[Pressed].getLabel());
                        ButtonArr[Pressed].setLabel(" ");
                        flag = Pressed;

                    }
                }


            }
    }
}

