package pac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPanel extends JPanel {

    private final Param p;
    private final Equation e;

    public DrawPanel(Param p,Equation e, JFrame f) {

        this.p = p ;
        this.e = e ;

        this.addMouseWheelListener( event -> {

            if (event.getWheelRotation()>0) {
                p.zoom = p.zoom/2 ;
                f.repaint();
            }
            if (event.getWheelRotation()<0) {
                p.zoom = p.zoom*2 ;
                f.repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                p.x=e.getX();
                p.y=e.getY();
                f.repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int x = 0 ;x<this.getWidth(); x++){

            for (int y = 0; y <this.getHeight(); y++) {

                render(g2d, x, y);
            }
        }
    }

    private void render(Graphics2D g2d, int x, int y) {

        Complex base = new Complex((0.0+x-p.x)/p.zoom,(0.0+y-p.y)/p.zoom);

        Color c = new Color(250,0,0) ;

        if (p.mandelbrot) {

            //les coef sont egaux au coordonnée
            p.coefIm = (0.0+x-p.x)/p.zoom ;
            p.coefRe = (0.0+y-p.y)/p.zoom ;

            Complex zero = new Complex(0,0);

            if (p.simple) {
                if (Equation.stagne(zero,e::fct, p.limit, 0, p.iteration)) {
                    g2d.setColor(Color.black);
                    g2d.drawRect(x, y, 0, 0);
                }
            }
            else {
                for (int i=1; i<=7; i++) {
                    g2d.setColor(c);

                    if (Equation.stagne(zero,e::fct, p.limit, 0, (p.iteration/7)*i)) {
                        g2d.drawRect(x, y, 0, 0);
                    }

                    c = new Color(250-((250/7)*i),c.getGreen(),c.getBlue());
                }
            }

            return;
        }

        if (p.burningShip) {

            p.coefIm = (0.0+x-p.x)/p.zoom ;
            p.coefRe = (0.0+y-p.y)/p.zoom ;

            Complex zero = new Complex(0,0);

            if (p.simple) {
                if (Equation.stagne(zero,e::burningShip, p.limit, 0, p.iteration)) {
                    g2d.setColor(Color.black);
                    g2d.drawRect(x, y, 0, 0);
                }
            }
            else {
                for (int i=1; i<=7; i++) {
                    g2d.setColor(c);

                    if (Equation.stagne(zero,e::burningShip, p.limit, 0, (p.iteration/7)*i)) {
                        g2d.drawRect(x, y, 0, 0);
                    }

                    c = new Color(250-((250/7)*i),c.getGreen(),c.getBlue());
                }
            }

            return;
        }

        if (p.simple) {

            g2d.setColor(Color.black);

            if (Equation.stagne(base,e::fct, p.limit, 0, p.iteration))
                g2d.drawRect(x,y,0,0);
        }
        else {

            for (int i=1; i<=7; i++) {
                g2d.setColor(c);

                if (Equation.stagne(base, e::fct, p.limit, 0, (p.iteration/7)*i)) {
                    g2d.drawRect(x, y, 0, 0);
                }
                else {
                    return;
                }

                c = new Color(250-((250/7)*i),c.getGreen(),c.getBlue());
            }
        }
    }
}