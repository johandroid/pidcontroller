/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pidcontroller2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JToggleButton;

/**
 *
 * @author thorsten
 */
public class NewJApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        setLayout(new BorderLayout());
        final NewJPanel p = new NewJPanel();
        add(p, BorderLayout.CENTER);
        final JToggleButton b = new JToggleButton("Run");
        add(b, BorderLayout.SOUTH);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.run = b.isSelected();
            }
        });
    }
    // TODO overwrite start(), stop() and destroy() methods
}
