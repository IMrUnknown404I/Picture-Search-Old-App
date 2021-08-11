package picmngr;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ruslan
 */
public class Help extends JFrame{
    protected static int pic=0;
    protected static byte[] pix=null;
    protected static boolean def;
    protected static boolean change=true;
    protected static boolean place=true;
    protected static boolean dialog=true;
    public static boolean text=false;
    protected static double percent=0; 
    public static int kolvo=0;
    
    protected static File listing;
    
    protected static int size1; protected static int size2;
    protected static int size_d1; protected static int size_d2;
    protected static File image1; protected static File image2;
    protected static File[] imagesList = null;
    protected static List<File> sumList= new ArrayList<File>();
    
    final JLabel label = new JLabel(); final JLabel label11 = new JLabel();
    final JButton but_Simple = new JButton("Simple Search");
    final JButton but_Obj = new JButton("Similar Search");
    final JButton but_Folder = new JButton("Change Folder");
    final JButton but_Instr = new JButton("Instructions");
    final JLabel l1 = new JLabel(); final JLabel l2 = new JLabel(); final JLabel l3 = new JLabel(); 
    final JLabel l4 = new JLabel(); final JLabel l5 = new JLabel(); final JLabel l6 = new JLabel();
    final JLabel Me = new JLabel();
    
    protected static File firstInc=null;
    EventReact eHandler = new EventReact();
    
    Help(){
        super("Image Manager");
        this.setSize(265, 340);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAutoRequestFocus(true);
        this.setLayout(new FlowLayout());
        
        Font font_but = new Font(Font.DIALOG, 14, 12);
        Font font_lab = new Font("Main", Font.ROMAN_BASELINE, 17);
        Font font_lab2 = new Font("Main", Font.ROMAN_BASELINE, 12);
        Font font_lab3 = new Font("Main", Font.ITALIC, 9);
        Dimension dim = new Dimension(140,40);
        Cursor hand = new Cursor(12);
        
        label.setText("Welcome to the Rusya's"); label.setFont(font_lab);
        add(label);
        label11.setText("Picture Manager!"); label11.setFont(font_lab);
        add(label11);
        l5.setPreferredSize(new Dimension(250,1)); add(l5);
        l1.setText("Choose the searching method.");
        l1.setFont(font_lab2); add(l1);
        l2.setPreferredSize(new Dimension(250,5)); add(l2);
        but_Simple.setFont(font_but); but_Simple.setPreferredSize(dim);
        but_Simple.setCursor(hand); add(but_Simple);
        l3.setPreferredSize(new Dimension(250,5)); add(l3);
        but_Obj.setFont(font_but); but_Obj.setPreferredSize(dim);
        but_Obj.setCursor(hand); add(but_Obj);
        l4.setPreferredSize(new Dimension(250,5)); add(l4);
        but_Folder.setFont(font_but); but_Folder.setPreferredSize(new Dimension(120,40));
        but_Folder.setCursor(hand); add(but_Folder);
        l6.setPreferredSize(new Dimension(250,5)); add(l6);
        but_Instr.setFont(font_but); but_Instr.setPreferredSize(new Dimension(100,40));
        but_Instr.setCursor(hand); add(but_Instr);
        
        but_Simple.addActionListener(eHandler);
        but_Obj.addActionListener(eHandler);
        but_Instr.addActionListener(eHandler);
        but_Folder.addActionListener(eHandler);
        
        Me.setText("by Subaev"); Me.setFont(font_lab3);
        Me.setPreferredSize(new Dimension(250,15)); 
        add(Me);
    }
    protected static void Listing(){
        if(listing==null){
            File file = new File("D:\\jaba_files\\images");
            imagesList = file.listFiles();
            //for(int i=0; i<imagesList.length;i++)
            //out.println(imagesList[i]);
        }else{
                imagesList = listing.listFiles();
                //for(int i=0; i<imagesList.length;i++)
                    //out.println(imagesList[i]);
            }
    }
    private void visible(){
            if(this.isVisible()==true){
                this.setVisible(false);
            } else { this.setVisible(true); }
        }
    
    public class EventReact implements ActionListener{
        public void actionPerformed(ActionEvent e) {  //All-events reader
           
            if(e.getSource()==but_Simple){  //getSource- output the 'place'
                def=true;
                visible();
                picmngr.GUI.Start();
            }
            if(e.getSource()==but_Obj){  //getSource- output the 'place'
                def=false;
                visible();
                picmngr.GUI.Start();
            }
            if(e.getSource()==but_Instr){
                String message="Welcome to the images manager!\nThere are some searching algorytms: simple and object-serch.\n"
                                 + "Simple one is searching only duplicates but similar-ones is looking for any similar pictures.\n"
                                    + "After this selection you will have to choose the input image for calculations in any folder.\n"
                                       + "After all the calculations you can see all exists (or zero, if it's not included), take a part in Bonus* and finally close the manager.\n"
                                          + "You also can change main folder or choose another image if previous one wasn't exist.\n"
                                             + "(!) At the last stage (finded image processing) you can change and save image by special filters.\n"
                                               + "* --> until Bonus wouldn't finished the programm wouldn't go further!\n"
                                                 + "Many thanks,\nDeveloped by Subaev Ruslan."; 
                JOptionPane.showMessageDialog(rootPane, message, "Read_me", JOptionPane.PLAIN_MESSAGE);
            }
            if(e.getSource()==but_Folder){  //getSource- output the 'place'
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    listing = fileopen.getSelectedFile();
                }
            }
        }
    }
    
    protected static void Dialog() throws Exception, IOException, InterruptedException{
        int cim1 = 0, cim2 = 0;
        
        image2= new File(GUI.path);
            
        for(int col=0;col<imagesList.length; col++){
            if(!imagesList[col].getName().contains("png") && !imagesList[col].getName().contains("jpg")) { }
            else {
                File image1 = imagesList[col];
                //out.println("CURRENT IMAGESLIST OBJ: "+imagesList[col]);
                //out.println("NOW CALCING THE PIC: "+image1);
                if(def==true){
                    if(change){
                        imagEq(image2, col);
                    }
                    imagEq(image1, col);
                    
                    if(!check(size_d1,size_d2)){
                    } else{
                        if(percent==(double)0){
                            //out.println("percent IS:  "+percent);
                            if(firstInc==null) firstInc=image1;
                            sumList.add(image1); kolvo++;
                            //out.println("ADDING TO SUMLIST PIC "+image1);
                        } //else
                            //out.println("percent IS:  "+percent);
                    }
                }
                else{
                    if(change){
                        imagEq(image2, col);
                    }
                    imagEq(image1, col);
                    
                    if(!check(size_d1,size_d2)){
                    } else{
                        if(percent<=35.0){
                            //out.println("percent IS:  "+percent);
                            if(firstInc==null) firstInc=image1;
                            sumList.add(image1); kolvo++;
                            //out.println("ADDING TO SUMLIST PIC "+image1);
                        } //else
                            //out.println("percent IS:  "+percent);
                    }
            }
        }}
        
        if(def){
            //out.println(sumList.toString());
            if(sumList.isEmpty())
                    PicsCreate(3);
                else{
                    Pics.AffOp appp= new Pics.AffOp(image2);
                    picmngr.PicOutFilt app2 = new picmngr.PicOutFilt(firstInc);
                    String message="Finded path to equal picture(s) below.\r\n";
                    for(int i=0; i<kolvo;i++){
                        message+=sumList.get(i).toString();
                        message+="\r\n";
                    } message+="\r\nCreated by Subaev (c)";
                    
                    int number=0;
                    boolean created=creating(); 
                    if(!created)
                        number= reading();
                    if(number!=0){
                        String recording="\r\n\r\n"+number+" Notation:\r\n";
                        translate(recording);
                    }
                    else translate("1-st Notation:\r\n");
                    
                    translate(message);
                    PicsCreate(2);
                }
        }else{
            //out.println(sumList.toString());
            if(sumList.isEmpty())
                    PicsCreate(3);
                else{
                    Pics.AffOp appp= new Pics.AffOp(image2);
                    picmngr.PicOutFilt app2 = new picmngr.PicOutFilt(firstInc);
                    String message="Finded path to similar picture(s) below.\r\n";
                    for(int i=0; i<kolvo;i++){
                        message+=sumList.get(i).toString();
                        message+="\r\n";
                    } message+="\r\nCreated by Subaev (c)";
                    
                    int number=0;
                    boolean created=creating(); 
                    if(!created)
                        number= reading();
                    if(number!=0){
                        number++;
                        String recording="\r\n\r\n"+number+" Notation:\r\n";
                        translate(recording);
                    } 
                    else translate("1-st Notation:\r\n");
                    
                    translate(message);
                    PicsCreate(2);
                }
        } 
    }
    private static boolean check(int x, int y){
        if(x==size1 && y==size2 || x==size_d1 && y==size_d2)
            { return true; }
        else { return false; }
    }
    private static int[] convert(byte buf[]) {
		int intArr[] = new int[buf.length / 4];
		int offset = 0;
		for (int i = 0; i < intArr.length; i++) {
			intArr[i] = (buf[3 + offset] & 0xFF)
					| ((buf[2 + offset] & 0xFF) << 8)
					| ((buf[1 + offset] & 0xFF) << 16)
					| ((buf[0 + offset] & 0xFF) << 24);
			offset += 4;
		}
		return intArr;
	}
    private static int imagEq(File filepath, int x) throws FileNotFoundException, IOException{
		
		BufferedImage srct = null;
		try {
			srct = ImageIO.read(new FileInputStream(filepath));
		} catch (FileNotFoundException e) {
 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int width = srct.getWidth();
		int height = srct.getHeight();
                if(change){
                    size1= width;
                    size2= height;
                    change=false;
                } else{
                    size_d1= width;
                    size_d2= height;
                    change=false;
                }
		//out.println("Picture at "+filepath+":  width: " + width + " height: " + height);
                    
		int sumcot = 0;
                if(!change){
                    BufferedImage bsrct1 = null; bsrct1 = ImageIO.read(new FileInputStream(image2));
                    BufferedImage bsrct2 = null; bsrct2 = ImageIO.read(new FileInputStream(imagesList[x]));
                    objcalc(bsrct1, bsrct2);
                    return 0;
                } return 0;
	}
        
        private static double objcalc(BufferedImage img1, BufferedImage img2){
            if(img1.getWidth()!=img2.getWidth() || img1.getHeight()!=img2.getHeight()){
                percent= 666.0;
                return   666.0;
            }
            
            int width = img1.getWidth();
            int height = img1.getHeight();
 
            long diff = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
                }
            }
            long maxDiff = 3L * 255 * width * height;
            
            percent= 100.0 * diff / maxDiff;
            return 100.0 * diff / maxDiff;
            }
        private static int pixelDiff(int rgb1, int rgb2) { //0xff nneds to safe the bytes while converting to int. 
            int r1 = (rgb1 >> 16) & 0xff;                  //& требует аргумент, а вот уже 0xff просто сохраняет последние 8 бит, 
            int g1 = (rgb1 >>  8) & 0xff;                  //т.к. == 1111 1111 (255 в десятичной)
            int b1 =  rgb1        & 0xff;
            int r2 = (rgb2 >> 16) & 0xff;
            int g2 = (rgb2 >>  8) & 0xff;
            int b2 =  rgb2        & 0xff;
            return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
        }
        private static void translate(String text){
            File file = new File("src\\Calculations.txt");        // input-sector-method
            FileWriter fr = null;
            
            try 
            {
                fr = new FileWriter(file,true);
                fr.write(text);
            } 
            catch (IOException e) { e.printStackTrace(); }
            finally
            {
                try { fr.close(); } 
                catch (IOException e) { e.printStackTrace(); }
            }
        }
        private static boolean creating() throws UnsupportedEncodingException, FileNotFoundException {
            if (exists("src\\Calculations.txt") == false) {
                try {
                    PrintWriter writer = new PrintWriter("src\\Calculations.txt", "CP1251");
                    writer.close();
                    return true;
                } 
                catch(Exception e) {}
            } 
            return false;
        }
        private static int reading() throws FileNotFoundException {
            int number=0;
            File file = new File("src\\Calculations.txt");
            FileReader  txt = new FileReader (file);
            Scanner scan = new Scanner(txt);
        
            if(scan.hasNextLine()==false) { return 0; }
            while(scan.hasNextLine()) { 
                String str= scan.nextLine();
                if(str.contains("Created by Subaev (c)")) number++;
            } 
            number++;
            return number;
        }
        private static boolean exists(String filepath) {
            File file = new File(filepath);
            return file.exists();
        }
        private static void PicsCreate(int x) throws IOException{
            //out.println("PICSCREATE - 1 ?    x= " + x);
            Pics app = new Pics(x); 
        }
}