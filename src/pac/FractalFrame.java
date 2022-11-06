package pac;


import javax.swing.*;
import java.awt.*;

public class FractalFrame extends JFrame {

    public FractalFrame(Param p,Equation e) {

        super("Fractal");

        DrawPanel    d = new DrawPanel(p,e,this) ;
        ControlPanel c = new ControlPanel(p,this) ;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setSize(p.tailleFrame,p.tailleFrame);

        this.setLayout(new BorderLayout());

        this.add(d,BorderLayout.CENTER);
        this.add(c,BorderLayout.EAST);

        this.setVisible(true);
    }
}
