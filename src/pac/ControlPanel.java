package pac;


import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    public ControlPanel(Param p, JFrame f) {

        this.setSize(p.tailleFrame/3,p.tailleFrame);

        JPanel          p2 = new JPanel(new GridLayout(6, 0));
        JPanel          p3 = new JPanel(new GridLayout(6, 0));
        JPanel          p4 = new JPanel();

        JLabel          indicIm    = new JLabel("" + p.coefIm  );
        JLabel          indicRe    = new JLabel("" + p.coefRe  );
        JLabel          indicLimit = new JLabel("" + p.limit   );
        JLabel          indicIter  = new JLabel("" + p.iteration);

        Scrollbar       coefIm = new Scrollbar(Scrollbar.HORIZONTAL, (int) p.coefIm, 10, -100, 300);
        Scrollbar       coefRe = new Scrollbar(Scrollbar.HORIZONTAL, (int)p.coefRe , 10, -100, 300);
        Scrollbar       limit  = new Scrollbar(Scrollbar.HORIZONTAL, p.limit       , 10,    0, 1_000_000);
        Scrollbar       iter   = new Scrollbar(Scrollbar.HORIZONTAL, p.iteration   , 10,    0, 1_000);

        JRadioButton    choix      = new JRadioButton("simple") ;
        JRadioButton    mandelbrot = new JRadioButton("mandelbrot") ;
        JRadioButton    burningShip= new JRadioButton("burning ship") ;

        this.setLayout(new GridLayout(3,1,10,10));

        p2.add(new JLabel("coef reel :"));
        p2.add(indicRe) ;
        p2.add(coefRe);

        p2.add(new JLabel("coef imaginaire :"));
        p2.add(indicIm) ;
        p2.add(coefIm);

        p3.add(new JLabel("limit :"));
        p3.add(indicLimit) ;
        p3.add(limit);
        p3.add(new JLabel("iter :"));
        p3.add(indicIter) ;
        p3.add(iter);

        p4.add(choix) ;
        p4.add(mandelbrot) ;
        p4.add(burningShip);

        coefRe.addAdjustmentListener(e -> {
            p.coefRe = e.getValue()/100.0 ;
            indicRe.setText(""+p.coefRe);
            f.repaint();
        });

        coefIm.addAdjustmentListener(e -> {
            p.coefIm = e.getValue()/100.0 ;
            indicIm.setText(""+p.coefIm);
            f.repaint();
        });

        limit.addAdjustmentListener(e -> {
            p.limit = e.getValue() ;
            indicLimit.setText(""+p.limit);
            f.repaint();
        });

        iter.addAdjustmentListener(e -> {
            p.iteration = e.getValue() ;
            indicIter.setText(""+p.iteration);
            f.repaint();
        });

        choix.addActionListener(e -> {
            p.simple = !p.simple;
            f.repaint();
        });

        mandelbrot.addActionListener( e-> {
            p.mandelbrot = !p.mandelbrot ;

            if (p.burningShip) {
                p.burningShip = false;
                burningShip.setSelected(false);
            }
            f.repaint();
        });

        burningShip.addActionListener( e-> {
            p.burningShip = !p.burningShip ;

            if (p.mandelbrot) {
                p.mandelbrot = false;
                mandelbrot.setSelected(false);
            }

            f.repaint();
        });

        this.add(p2);
        this.add(p3);
        this.add(p3);
        this.add(p4);
    }
}