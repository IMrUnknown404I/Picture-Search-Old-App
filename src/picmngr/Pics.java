package picmngr;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static picmngr.GUI.path;
import static picmngr.Help.kolvo;
import static picmngr.Help.sumList;
/**
 *
 * @author Ruslan
 */
public class Pics extends JFrame{
    protected static int pic=0;
    protected static boolean place=true;
    protected static boolean open=true;
    static String image1="";
    static String image2="";
    
    final JLabel label = new JLabel();
    final JButton but_Mod = new JButton("MODIFY LISTS");
    final JButton but_Calc = new JButton("CALCULATIONS");
    final JLabel l1 = new JLabel(); final JLabel l2 = new JLabel();
    static JFrame frame2;
    
    public Pics(int x) throws IOException{
        switch(x){
            case 2: //out.println("CASE 2 ?");
                End app1= new End();
                app1.DoneCreate();
            case 3: //out.println("CASE 3 ?");
                End app2= new End();
                app2.WrongCreate();
                break;
        }
    }
    protected static void setter(String str1, String str2){
        image1=str1;
        image2=str2;
    }
        protected void Test() throws IOException {
            JFrame frame = new JFrame();
            frame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            // Загружаем изображение
            BufferedImage img = ImageIO.read(new File(image1));
            JLabel label = new JLabel(new ImageIcon(img));
                frame.setTitle(image1);
                int wid= img.getWidth();
                int heig= img.getHeight();
                frame.setBounds(pic, 50, wid, heig);
                frame.setLayout(new FlowLayout());
                Dimension dim= frame.getSize();
                label.setMaximumSize(dim);
                frame.add(label);
        }
    protected static class AffOp {
        protected static BufferedImage bi;
        protected static Graphics2D g2d;

        public AffOp(File file) throws IOException{
            JFrame frame1 = new JFrame(); JFrame frame2 = new JFrame();
            frame1.setDefaultLookAndFeelDecorated(true); frame2.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            // Загружаем изображение
            BufferedImage img = ImageIO.read(file);
            JLabel label = new JLabel(new ImageIcon(img));
            
            String str=""; 
            for(int i=14; i<path.length()-4;i++){
                str+=path.charAt(i);
            }
            frame1.setTitle(str);
            int wid= img.getWidth();
            int heig= img.getHeight();
            
            frame1.setBounds(pic, 50, wid+25, heig+40);
            frame2.dispose();
            frame1.setLayout(new FlowLayout());
            Dimension dim= frame1.getSize();
            label.setMaximumSize(dim);
            frame1.add(label); frame1.setVisible(true);
        }
    }
}
    class End extends JFrame{
    protected void DoneCreate() throws IOException{ //out.println("DONE CREATE - 2 ?");
        Done app= new Done();
    }
    protected void WrongCreate(){
        Wrong app2= new Wrong();
    }
    final JLabel label11 = new JLabel();
    final JLabel label113 = new JLabel();
    final JButton but_Game1 = new JButton("Bonus");
    final JButton but_Info11 = new JButton("Get information");
    final JButton but_Exit1 = new JButton("EXIT");
    final JLabel l111 = new JLabel(); final JLabel l2 = new JLabel(); final JLabel l3 = new JLabel();
    
    EventReact eHandler1 = new EventReact();
    
    protected class Done extends JFrame{
        final EventReact eHandler = new EventReact();
        public Done(){ //out.println("DONE METHOD - 3 ?");
            if(Pics.open==false){
                return;
            } else Pics.open=false;
        
            JFrame frame1=new JFrame();
            frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame1.setSize(250, 290); //215
            frame1.setResizable(false);
            frame1.setLocationRelativeTo(null);
            frame1.setAutoRequestFocus(true);
            frame1.setLayout(new FlowLayout());
            
            Font font_but = new Font(Font.DIALOG, 14, 12);
            Font font_lab = new Font("Main", Font.ROMAN_BASELINE, 16);
            Dimension dim = new Dimension(135,40);
            Cursor hand = new Cursor(12);
        
            label11.setText("Good news!"); label11.setFont(font_lab);
            label11.setAlignmentX(CENTER_ALIGNMENT); frame1.add(label11);
            label113.setText("Simular image(s) finded!"); label113.setFont(font_lab);
            label113.setAlignmentX(CENTER_ALIGNMENT); frame1.add(label113);
            l111.setPreferredSize(new Dimension(250,20)); 
            frame1.add(l111);
            but_Info11.setFont(font_but); but_Info11.setPreferredSize(dim);
            but_Info11.setCursor(hand); frame1.add(but_Info11);
            l2.setPreferredSize(new Dimension(250,10)); frame1.add(l2);
            but_Game1.setFont(font_but); but_Game1.setPreferredSize(dim);
            but_Game1.setCursor(hand); frame1.add(but_Game1);
            l3.setPreferredSize(new Dimension(250,10)); frame1.add(l3);
            but_Exit1.setFont(font_but); but_Exit1.setPreferredSize(new Dimension(110,40));
            but_Exit1.setCursor(hand); frame1.add(but_Exit1);
            
            but_Game1.addActionListener(eHandler1);
            but_Info11.addActionListener(eHandler1);
            but_Exit1.addActionListener(eHandler1);
            frame1.setVisible(true);
            //out.println("DONE CReATED ?    TEXT IS: "+label11.getText());
            }
    }
    
    final JLabel label1 = new JLabel();
    final JButton but_Res = new JButton("Choose another pic");
    final JButton but_Game2 = new JButton("Bonus");
    final JButton but_Exit2 = new JButton("EXIT");
    final JLabel l11 = new JLabel(); final JLabel l22 = new JLabel(); final JLabel l23 = new JLabel();
    final EventReact eHandler2 = new EventReact();
    
    protected class Wrong extends JFrame {
        public Wrong(){ //out.println("WRONG METHOD ? ?");
            if(Pics.open==false){
                return;
            } else Pics.open=false;
        
            JFrame frame2=new JFrame();
            frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame2.setSize(250, 260);
            frame2.setResizable(false);
            frame2.setLocationRelativeTo(null);
            frame2.setAutoRequestFocus(true);
            frame2.setLayout(new FlowLayout());
            
            Font font_res = new Font(Font.DIALOG, 14, 13);
            Font font_but = new Font(Font.DIALOG, 14, 12);
            Font font_lab = new Font("Main", Font.ROMAN_BASELINE, 16);
            Dimension dim = new Dimension(135,40);
            Cursor hand = new Cursor(12);
        
            label1.setText("This image doesn't exists!"); label1.setFont(font_lab);
            frame2.add(label1);
            l23.setPreferredSize(new Dimension(250,20));
            frame2.add(l23);
            but_Res.setFont(font_res); but_Res.setPreferredSize(new Dimension(155,45));
            but_Res.setCursor(hand); frame2.add(but_Res);
            l11.setPreferredSize(new Dimension(250,7));
            frame2.add(l11);
            but_Game2.setFont(font_but); but_Game2.setPreferredSize(dim);
            but_Game2.setCursor(hand); frame2.add(but_Game2);
            l22.setPreferredSize(new Dimension(250,7)); frame2.add(l22);
            but_Exit2.setFont(font_but); but_Exit2.setPreferredSize(new Dimension(100, 35));
            but_Exit2.setCursor(hand); frame2.add(but_Exit2);
            
            but_Res.addActionListener(eHandler2);
            but_Game2.addActionListener(eHandler2);
            but_Exit2.addActionListener(eHandler2); visible();
            //out.println("WRONG CREATED ? ? TEXT IS: "+label1.getText());
            frame2.setVisible(true);
        }
    }
    private void visible(){
            this.setVisible(false);
        }
    public class EventReact implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==but_Game1||e.getSource()==but_Game2){
                JOptionPane.showMessageDialog(rootPane, "Look at the command line.\nHave fun & *Finish it!. :)", "Tic-Tac-Toe", JOptionPane.PLAIN_MESSAGE);
                Game.main();
            }
            if(e.getSource()==but_Exit1||e.getSource()==but_Exit2){
                System.exit(0);
            }
            if(e.getSource()==but_Info11){  
                String message="Finded path to similar pictires below.\n";
                for(int i=0; i<kolvo;i++){
                    message+=sumList.get(i).toString();
                    message+="\n";
                }
                JOptionPane.showMessageDialog(rootPane, message, "Nice", JOptionPane.PLAIN_MESSAGE);
            }
            if(e.getSource()==but_Res){
                visible();
                Help newone = new Help();
                newone.setDefaultLookAndFeelDecorated(true);
                newone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newone.setVisible(true);
            }
        }
    }
  }
