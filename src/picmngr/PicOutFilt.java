package picmngr;

/**
 *
 * @author Ruslan
 */
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import marvin.util.*;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;
import static java.lang.System.out;
import java.util.List;
import static marvin.MarvinPluginCollection.findTextRegions;
import marvin.image.MarvinSegment;

import static picmngr.GUI.path;
import static picmngr.Help.firstInc;
 
public class PicOutFilt extends JFrame
{ 
    private MarvinImagePanel imagePanel;
	private MarvinImage  image, 
			     backupImage;
	private JPanel 	panelBottom;
	private JButton buttonGray, 
			buttonEdgeDetector, 
			buttonInvert, 
			buttonReset,
                        buttonSave,
                        buttonPix;
	private MarvinImagePlugin imagePlugin;
        protected static int minW=480, minH=108;
        private boolean poz=false;
        
    private static File filep;
    EventReact eHandler = new EventReact();
    public PicOutFilt(File file) 
    { 
        super("Filters Sample");  //out.println("STARTING PIC ???");
        filep=file;
        String dubl= firstInc.toString(); 
        String str="";
        for(int i=21; i<dubl.length()-4;i++){
            str+=dubl.charAt(i);
        }
        this.setTitle(str);
        Color IndianRed = new Color(255, 160, 122);
        
        // Create Graphical Interface 
        Cursor hand = new Cursor(12);
        buttonGray = new JButton("Gray"); buttonGray.setCursor(hand); buttonGray.setBackground(IndianRed);
	buttonGray.addActionListener(eHandler);
	buttonEdgeDetector = new JButton("EdgeDetector"); buttonEdgeDetector.setCursor(hand); buttonEdgeDetector.setBackground(IndianRed);
	buttonEdgeDetector.addActionListener(eHandler);
	buttonInvert = new JButton("Invert"); buttonInvert.setCursor(hand); buttonInvert.setBackground(IndianRed);
	buttonInvert.addActionListener(eHandler);
        buttonPix = new JButton("Pixel"); buttonPix.setCursor(hand); buttonPix.setBackground(IndianRed);
	buttonPix.addActionListener(eHandler);
	buttonReset = new JButton("Reset"); buttonReset.setCursor(hand); buttonReset.setBackground(IndianRed);
	buttonReset.addActionListener(eHandler);
        buttonSave = new JButton("Save"); buttonSave.setCursor(hand); buttonSave.setBackground(IndianRed);
	buttonSave.addActionListener(eHandler);
		
	panelBottom = new JPanel();
	panelBottom.add(buttonGray);
	panelBottom.add(buttonEdgeDetector);
	panelBottom.add(buttonInvert);
        panelBottom.add(buttonPix);
	panelBottom.add(buttonReset);
        panelBottom.add(buttonSave);
         
        // Load image 
        //out.println("LOAD IMAGE");
        image = MarvinImageIO.loadImage(file.toString());
        backupImage = image.clone();
        
        int wid= image.getWidth();
        int heig= image.getHeight();
        if(wid<minW)  { wid=minW; poz=true;  }
        if(heig<minH) { heig=minH; poz=true; }
        this.setBounds(image.getWidth()+50, 35, wid, heig+65);
        
        imagePanel = new MarvinImagePanel(); //out.println("MaevinIMAGE");
	imagePanel.setImage(image); //imagePanel.setSize(new Dimension(wid,heig));
        
        // ImagePanel 
	Container l_c = getContentPane();
	l_c.setLayout(new BorderLayout());
//        Container l_c = new Container(); 
//        l_c.setLayout(new FlowLayout(FlowLayout.CENTER));



        
	l_c.add(imagePanel, BorderLayout.CENTER);  //, BorderLayout.CENTER
	l_c.add(panelBottom, BorderLayout.SOUTH); //, BorderLayout.SOUTH
        l_c.setVisible(true);
        
        
        //this.setResizable(false);
        this.setVisible(true); //out.println("DONE");
    }
    
    
    public class EventReact implements ActionListener{
        public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == buttonGray){
                    image = backupImage.clone();
			imagePlugin = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.grayScale.jar");
			imagePlugin.process(image, image, null, MarvinImageMask.NULL_MASK, false);
                        image.update();
                        imagePanel.setImage(image);
		}
		if(e.getSource() == buttonEdgeDetector){
                    image = backupImage.clone();
			imagePlugin = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.edge.edgeDetector.jar");
			imagePlugin.process(image, image, null, MarvinImageMask.NULL_MASK, false);
                        image.update();
                        imagePanel.setImage(image);
		}
		if(e.getSource() == buttonInvert){
                    image = backupImage.clone();
			imagePlugin = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.invert.jar");
			imagePlugin.process(image, image, null, MarvinImageMask.NULL_MASK, false);
                        image.update();
                        imagePanel.setImage(image);
		}
                if(e.getSource() == buttonPix){
                    image = backupImage.clone();
			imagePlugin = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.blur.pixelize.jar");
			imagePlugin.process(image, image, null, MarvinImageMask.NULL_MASK, false);
                        image.update();
                        imagePanel.setImage(image);
		}
                if(e.getSource() == buttonSave){
                    MarvinImageIO.saveImage(image, "src\\FilteredPic.png");
                }
                if(e.getSource() == buttonReset){
                    image = backupImage.clone();
                    image.update();
                    imagePanel.setImage(image);
                }
		
	}
}
}
