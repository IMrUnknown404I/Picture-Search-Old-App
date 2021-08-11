package picmngr;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Ruslan
 */
public class PicMngr {
    protected static Thread thread1= new Thread();
    private static byte[] arr1=null;
    private static byte[] arr2=null;
    
    public static void main(String[] args) throws IOException, InterruptedException{
        
        thread1.start();
        Help newone = new Help();
        newone.setDefaultLookAndFeelDecorated(true);
        newone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newone.setVisible(true);
        thread1.join();
    }
}
