package Puzzle;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle extends Frame implements ActionListener {
    private final static int n=3;
    private final static int Sum = n * n;
    private static int flag = n*n-1;
    private Button[] ButtonArr = new Button[Sum];
    private String[] ButtonName = Random(Sum);

    /*
     * @para len represents the length of the solution array
     *
     * function you input a length of an array,this method will return an array
     * of number from 1 to len with unsorted.
     */
    private boolean IsSolvable(Integer[] Arr){
        int Criterion=0;
        for(int i=0;i<Sum-1;i++)
            for(int j=0;j<i;j++)
                if(Arr[j]>Arr[i])
                    Criterion++;
        return Criterion % 2 == 0;
    }
    private String[] Random(int len) {
        Integer Arr[] = new Integer[len - 1];
        String S[] = new String[len];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len - 1; i++)
            list.add(i + 1);
       do{ Collections.shuffle(list);
        list.toArray(Arr);}
        while (!(IsAlreadyWin(Arr)||IsSolvable(Arr)));
        for (int i = 0; i < len - 1; i++)
            S[i] = Arr[i].toString();
        S[len - 1] = " ";
        return S;
    }

    public static void main(String[] argc) {
       /* Puzzle MainWindow = new Puzzle();
        MainWindow.setSize(new Dimension(600, 600));
        MainWindow.setTitle("15-square Puzzle");
        MainWindow.setVisible(true);*/
       BeginUI Begin=new BeginUI();
       Begin.setTitle("The Begin Page");
       Begin.setSize(new Dimension(600,600));
       Begin.setVisible(true);
    }
    private boolean IsAlreadyWin(Integer[] text){
        for(int i=0;i<Sum-1;i++)
            if(!(text[i]==i+1))
                return false;
        return true;
    }
private boolean IsWin(){
     for(int i=0;i<Sum-1;i++)
        if(!ButtonArr[i].getLabel().equals(""+(i+1) ))
          return false;
       return true;
}
    Puzzle() {
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
        if(IsWin()) {
            EndUI Win=new EndUI(this);
            Win.setTitle("Winner!");
            Win.setSize(600,600);
            Win.setVisible(true);
        }
    }
}
class EndUI extends Frame{
    EndUI(Puzzle P){
        P.dispose();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("GameOver!\nYou're the winner!",100,100);
    }

}
class BeginUI extends Frame implements ActionListener{
    BeginUI(){
        setLayout(new GridBagLayout());
        repaint();
        Button Begin=new Button("Begin");
        add(Begin);
        Begin.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        g.drawString("GameBegin!\nYou're supposed to be the winner!",100,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Puzzle MainWindow = new Puzzle();
        MainWindow.setSize(new Dimension(600, 600));
        MainWindow.setTitle("15-square Puzzle");
        MainWindow.setVisible(true);
    }
}

