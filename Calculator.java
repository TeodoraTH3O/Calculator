package java1a.curs7;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Teodora on 9/19/2019.
 */
public class Calculator extends Frame implements ActionListener{

    Panel p1 = new Panel();
    Panel p2 = new Panel(new GridLayout(4,4));
    TextField t1 = new TextField(20);
    Button[] b = new Button[16];
    int x,y;

    enum Operatie{
        adunare, scadere, inmultire,impartire
    }

    Operatie op = Operatie.adunare;

    public Calculator(){
        setLayout(new BorderLayout() );
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        p1.add(t1);
        t1.setText("0");

        for(int i=0;i<16;i++){
            b[i] = new Button();
            p2.add(b[i]);
            b[i].addActionListener(this);
        }

        b[0].setLabel("1");
        b[1].setLabel("2");
        b[2].setLabel("3");
        b[3].setLabel("+");

        b[4].setLabel("4");
        b[5].setLabel("5");
        b[6].setLabel("6");
        b[7].setLabel("-");

        b[8].setLabel("7");
        b[9].setLabel("8");
        b[10].setLabel("9");
        b[11].setLabel("*");

        b[12].setLabel("0");
        b[13].setLabel("C");
        b[14].setLabel("=");
        b[15].setLabel("/");

        pack();
        setResizable(false);
        setVisible(true);

        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent ev){
        Button bx = (Button) ev.getSource();
        try{
          x = x*10+Integer.parseInt(bx.getLabel());
          t1.setText(x+"");
        }catch(NumberFormatException e){
            switch(bx.getLabel()){
                case "+":
                    y=x;
                    x=0;
                    op=Operatie.adunare;
                break;

                case "-":
                    y=x;
                    x=0;
                    op=Operatie.scadere;
                break;

                case "*":
                    y=x;
                    x=0;
                    op=Operatie.inmultire;
                break;

                case "/":
                    y=x;
                    x=0;
                    op=Operatie.impartire;
                break;

                case "=":
                    switch(op) {
                        case adunare:
                            x+=y;
                            t1.setText(x+"");
                        break;
                        case scadere:
                            x=y-x;
                            t1.setText(x+"");
                        break;
                        case inmultire:
                            x*=y;
                            t1.setText(x+"");
                        break;
                        case impartire:
                            x=y/x;
                            t1.setText(x+"");
                        break;
                    }
                break;
                case "C":
                    x=0;
                    y=0;
                    op=Operatie.adunare;
                    t1.setText("0");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
