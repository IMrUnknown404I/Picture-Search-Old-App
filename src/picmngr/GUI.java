package picmngr;

import java.awt.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ruslan
 */
public class GUI{
    public static String path="";
    public static JButton button2 = new JButton("Accept");
    public static JButton button = new JButton("Choose image");
    
    static void Start() {
        
            javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            TestFrame testFrame = new TestFrame();
            testFrame.setVisible(true);
        });
    }
}

class TestFrame extends JFrame {
    EventReact eHandler = new EventReact();
    //public static JButton button = new JButton("Выбрать Изображение");
    final JLabel label = new JLabel("Selected File");
    final JLabel label2 = new JLabel("Path to the file");
    
    public TestFrame() {
        super("Choosing image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         
        panel.add(Box.createVerticalGlue());
 
        label.setAlignmentX(CENTER_ALIGNMENT);
        label2.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(label2);
        
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
 
        GUI.button2.setAlignmentX(CENTER_ALIGNMENT);
        GUI.button.setAlignmentX(CENTER_ALIGNMENT);
        
        panel.add(GUI.button2);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(GUI.button);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);
        GUI.button2.addActionListener(eHandler);
        GUI.button.addActionListener(eHandler);
        
        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void visible() throws IOException{
        if(this.isVisible()==true){
            this.setVisible(false);
        } else { this.setVisible(true); }
    }
    public class EventReact implements ActionListener{

        public void actionPerformed(ActionEvent e) {  //events reader
            if(e.getSource()==GUI.button){
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    if(!file.getName().contains("png") && !file.getName().contains("jpg"))
                        JOptionPane.showMessageDialog(rootPane, "Choose the IMAGE, please.", "Error", JOptionPane.PLAIN_MESSAGE);
                    else {
                        label.setText(file.getName());
                        label2.setText(file.getPath());
                    }
                }
                GUI.path=label2.getText();
            }
            if(e.getSource()==GUI.button2){
                if(label.getText().compareTo("Selected File")==0){
                    JOptionPane.showMessageDialog(rootPane, "Choose the pic before please.", "Error", JOptionPane.PLAIN_MESSAGE);
                } else{
                    try {
                    visible();
                } catch (IOException ex) {
                    Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
                    try {
                    Help.Listing();
                    Help.Dialog();
                } catch (Exception ex) {
                    Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        }
    }
}