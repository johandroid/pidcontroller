/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pidcontroller2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author thorsten
 */
public class NewJPanel extends javax.swing.JPanel {

    private final PID pid = new PID(new double[]{0.01}, new double[]{10, 10,10}, 10);
    private static final int w = 1000;
    private final double[] head = new double[]{w * 10.5, 400};
    private final double[] foot = new double[]{w * 10.5, 300};
    private final double[] headv = new double[]{0, 0};
    private double footvx = 0;
    private Image agent = null;
    public boolean run = false;

    /**
     * Creates new form NewJPanel
     */
    public NewJPanel() {
        initComponents();
        try {
            agent = ImageIO.read(getClass().getResource("agent.png"));
        } catch (IOException ex) {
            Logger.getLogger(NewJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] d = new double[]{head[0] - foot[0], head[1] - foot[1]};
                double[] d2 = new double[]{w * 10.5 - head[0], 200 - head[1]};
                double a = Math.atan2(d[0], -d[1]);

                double y = 0;
                if (run) {
                    y = pid.compute(-a, 0);
                }
                if (y > 2) {
                    y = 2;
                }
                if (y < -2) {
                    y = -2;
                }
                footvx += y;
                foot[0] += footvx;

                //System.out.println(a);
                double l = Math.sqrt(d[0] * d[0] + d[1] * d[1]);
                double x = 0.02 * (100 - l);
                headv[0] += x * d[0];
                headv[1] += x * d[1];
                headv[1] += 1;
                headv[0] *= 0.93;
                headv[1] *= 0.93;
                head[0] += headv[0];
                head[1] += headv[1];
                repaint();
            }
        }).start();

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        for (int i = 0; i < 30; ++i) {
            g.drawLine((int) head[0] - w * i, (int) head[1], (int) foot[0] - w * i, (int) foot[1]);
            g.drawArc((int) head[0] - w * i - 5, (int) head[1] - 5, 10, 10, 0, 360);
            if (agent != null) {
                g.drawImage(agent,
                        (int) foot[0] - w * i - agent.getWidth(null) / 2,
                        (int) foot[1] - agent.getHeight(null) / 2,
                        null);
            }
        }
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}