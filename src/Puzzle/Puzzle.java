package Puzzle;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle extends Frame implements ActionListener {
    private static int n;
    private static int Sum ;
    private static int flag ;
    private static ArrayList<Button> ButtonArr;
    /*
     * @para len represents the length of the solution array
     *
     * function you input a length of an array,this method will return an array
     * of number from 1 to len with unsorted.
     */
    private boolean IsSolvable(ArrayList<String> Arr) {
        int Criterion = 0;
        for (int i = 0; i < Sum ; i++)
            for (int j = 0; j < i; j++)
                if (Integer.valueOf(Arr.get(j)) > Integer.valueOf(Arr.get(i)))
                    Criterion++;
        final int flag = Arr.indexOf(Sum + "");
        if ((Criterion + flag/n +flag%n) % 2 == 0) {
            Arr.set(flag, " ");
            return true;
        }
        return false;
    }
    private ArrayList<String> Random(int len) {
        ArrayList<String> S=new ArrayList<>();
        for (int i = 0; i < len ; i++)
            S.add(i + 1+"");
       do{ Collections.shuffle(S);
           }
        while (!(!IsAlreadyWin(S)&&IsSolvable(S)));
        return S;
    }

    public static void main(String[] argc) {
       BeginUI Begin=new BeginUI();
       Begin.setTitle("The Begin Page");
       Begin.setSize(new Dimension(600,600));
       Begin.setVisible(true);
    }
    private boolean IsAlreadyWin(ArrayList<String> text){
        for(int i=0;i<Sum-1;i++) {
            if(!(Integer.valueOf(text.get(i)) ==i+1))
                return false;
        }
        return true;
    }
private boolean IsWin(){
     for(int i=0;i<Sum-1;i++)
        if(!ButtonArr.get(i).getLabel().equals(""+(i+1) ))
          return false;
       return true;
}
    Puzzle(int n) {
        Puzzle.n =n;
        Sum=n*n;
        ButtonArr= new ArrayList<>();
        ArrayList<String> buttonName=Random(Sum);
        flag= buttonName.indexOf(" ");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(new GridLayout(n, n));
        setFont(new Font("SansSerif", Font.BOLD, 24));
        for (int i = 0; i < Sum; i++){
            Button bak=(Button) add(new Button(buttonName.get(i)));
            ButtonArr.add(i, bak);
            ButtonArr.get(i).addActionListener(this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int Pressed = 0; Pressed < Sum; Pressed++)
            if (e.getSource() == ButtonArr.get(Pressed)) {
                if (Pressed / n == flag / n) {
                    if (Pressed - flag == 1 || flag - Pressed == 1) {
                        ButtonArr.get(flag).setLabel(ButtonArr.get(Pressed).getLabel());
                        ButtonArr.get(Pressed).setLabel(" ");
                        flag = Pressed;
                    }
                } else if (Pressed % n == flag % n) {
                    if (Pressed - flag == n || flag - Pressed == n) {
                        ButtonArr.get(flag).setLabel(ButtonArr.get(Pressed).getLabel());
                        ButtonArr.get(Pressed).setLabel(" ");
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
    }
    @Override
    public void paint(Graphics g) {
        g.drawString("GameOver!\nYou're the winner!",100,100);
    }

}
class BeginUI extends Frame implements ActionListener,ItemListener{
private final static Choice choice=new Choice();
static int n;
    BeginUI(){
        setLayout(new GridBagLayout());
        for (int i=2;i<13;i++)
            choice.add(i+"×"+i);
        choice.select("4×4");
     //   repaint();
        Button Begin=new Button("Begin");
        add(choice);
        choice.addItemListener(this);
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
        Puzzle MainWindow = new Puzzle(choice.getSelectedIndex()+2);
        MainWindow.setSize(new Dimension(600, 600));
        MainWindow.setTitle(choice.getSelectedIndex()+2+"-square Puzzle");
        MainWindow.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
repaint();
    }
}

