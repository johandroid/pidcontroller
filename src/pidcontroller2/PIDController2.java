/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pidcontroller2;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author thorsten
 */
public class PIDController2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JFrame f = new JFrame();
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setSize(1000, 600);
       f.setLayout(new BorderLayout());
       NewJApplet a = new NewJApplet();
       a.init();
       f.add(a,BorderLayout.CENTER);
       f.setVisible(true);
    }
}
