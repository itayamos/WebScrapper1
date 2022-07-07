import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Scrap extends JFrame implements ActionListener {
    public static final String LINK1="https://www.ice.co.il/";
    public static final String articleClass1="article-item_title";
    public static final String bodyClass1="content";
    public static final String LINK2="https://www.israelhayom.co.il/";
    public static final String articleClass2="post-title";
    public static final String bodyClass2="text-content";
    public static final String LINK3="https://www.kan-ashkelon.co.il/";
    public static final String articleClass3="transition_all c1";
    public static final String bodyClass3="contentBox";
    public static final String LINK4="https://www.srugim.co.il/";
    public static final String articleClass4="h3";
    public static final String bodyClass4="art_content";
    public static final String LINK5="https://www.now14.co.il/";
    public static final String articleClass5="post-title";
    public static final String bodyClass5="the-content";
    public static final int WINDOW_WIDTH=2000;
    public static final int WINDOW_HEIGHT=1000;
    public static final int WAITING_TIME=5000;
    public static final int MAX_QUEUE_SIZE=5;
    public static final int JL_HEIGHT=40;
    public static final int Y1_MARGIN=100;
    public static final int Y2_MARGIN=150;
    public static final int Y3_MARGIN=200;
    public static final int Y4_MARGIN=250;
    public static final int Y5_MARGIN=300;
    public static final int X=0;
    public static final int Y=0;
    public static final int TF_HEIGHT=40;
    public static final int TF_WIDTH=200;
    public static final int BTN_YnH=50;
    public static final int BTN_WIDTH=80;
    private ScrapHelper ice;
    private ScrapHelper israelHayom;
    private ScrapHelper kan;
    private ScrapHelper srugim;
    private ScrapHelper now14;
    private static JTextField textField;
    private JButton button;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    public Scrap(){
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jLabel1=new JLabel();
        jLabel2=new JLabel();
        jLabel3=new JLabel();
        jLabel4=new JLabel();
        jLabel5=new JLabel();
        textField=new JTextField();
        button=new JButton();
        textField.setBounds(X,Y,TF_WIDTH,TF_HEIGHT);
        button.setBounds(X,BTN_YnH,BTN_WIDTH,BTN_YnH);
        button.setText("submit");
        button.addActionListener(this);
        jLabel1.setBounds(X,Y1_MARGIN,WINDOW_WIDTH,JL_HEIGHT);
        jLabel2.setBounds(X,Y2_MARGIN,WINDOW_WIDTH,JL_HEIGHT);
        jLabel3.setBounds(X,Y3_MARGIN,WINDOW_WIDTH,JL_HEIGHT);
        jLabel4.setBounds(X,Y4_MARGIN,WINDOW_WIDTH,JL_HEIGHT);
        jLabel5.setBounds(X,Y5_MARGIN,WINDOW_WIDTH,JL_HEIGHT);

        this.add(textField);
        this.add(button);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jLabel4);
        this.add(jLabel5);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        new Thread(()->{
            Queue<String> q=new LinkedList<>();
            ArrayList<String> trash=new ArrayList<>();
            try {
                ice=new ScrapHelper(LINK1,articleClass1,bodyClass1);
                israelHayom=new ScrapHelper(LINK2,articleClass2,bodyClass2);
                kan=new ScrapHelper(LINK3,articleClass3,bodyClass3);
                srugim=new ScrapHelper(LINK4,articleClass4,bodyClass4);
                now14=new ScrapHelper(LINK5,articleClass5,bodyClass5);

                LinkedList<String> l1;
                LinkedList<String> l2;
                LinkedList<String> l3;
                LinkedList<String> l4;
                LinkedList<String> l5;
                if(AE.getSource()==button && !textField.getText().equals("")){
                    String s=textField.getText();
                    l1 = ice.search(s);
                    l2 = israelHayom.search(s);
                    l3 = kan.search(s);
                    l4 = srugim.search(s);
                    l5 = now14.search(s);
                    while (true) {
                        if (!l1.isEmpty() && !trash.contains(l1.peekFirst())) {
                            trash.add(l1.peekFirst());
                            q.add(l1.peekFirst());
                            l1.removeFirst();
                        }
                        if (!l2.isEmpty() && !trash.contains(l2.peekFirst())) {
                            trash.add(l2.peekFirst());
                            q.add(l2.peekFirst());
                            l2.removeFirst();
                        }
                        if (!l3.isEmpty() && !trash.contains(l3.peekFirst())) {
                            trash.add(l3.peekFirst());
                            q.add(l3.peekFirst());
                            l3.removeFirst();
                        }
                        if (!l4.isEmpty() && !trash.contains(l4.peekFirst())) {
                            trash.add(l4.peekFirst());
                            q.add(l4.peekFirst());
                            l4.removeFirst();
                        }
                        if (!l5.isEmpty() && !trash.contains(l5.peekFirst())) {
                            trash.add(l5.peekFirst());
                            q.add(l5.peekFirst());
                            l5.removeFirst();
                        }
                        while (q.size() > MAX_QUEUE_SIZE) {
                            q.poll();
                        }

                        jLabel1.setText(q.toArray()[0].toString());
                            if (q.size()>1){
                                jLabel2.setText(q.toArray()[1].toString());
                            }
                            if (q.size()>2){
                                jLabel3.setText(q.toArray()[2].toString());
                            }
                            if (q.size()>3){
                                jLabel4.setText(q.toArray()[3].toString());
                            }if (q.size()>4) {
                            jLabel5.setText(q.toArray()[4].toString());
                            }
                        try {
                            Thread.sleep(WAITING_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();

    }
    public static void main(String[] args) {
        new Scrap();
    }
}
