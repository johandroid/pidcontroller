/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pidcontroller2;

/**
 *
 * @author thorsten
 */
public class PID {

    private double[] gi;
    private double[] gd;
    private double gp;
    private double[] is;
    private double[] ds;
    private double[] prev;

    public PID(double[] gi, double[] gd, double gp) {
        this.gi = gi;
        this.gd = gd;
        this.gp = gp;

        this.is = new double[gi.length];
        this.prev = new double[gd.length];
        this.ds = new double[gd.length];
    }

    public double compute(double ist, double soll) {
        double e = soll - ist;

        is[0] += e;
        for (int i = 1; i < is.length; ++i) {
            is[i] += is[i - 1];
        }

        ds[0] = e - prev[0];
        prev[0] = e;
        for(int i = 1;i < gd.length;++i){
            ds[i] = ds[i - 1] - prev[i];
            prev[i] = ds[i - 1];
        }
        
        double sum = gp * e;
        for (int i = 0; i < is.length; ++i) {
            sum += gi[i] * is[i];
        }
        for (int i = 0; i < ds.length; ++i) {
            sum += gd[i] * ds[i];
        }
        return sum;
    }
}
