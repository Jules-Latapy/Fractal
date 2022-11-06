import pac.Equation;
import pac.FractalFrame;
import pac.Param;

public class Main {

    public static Param p = new Param() ;

    public static Equation e = new Equation(p) ;
    public static FractalFrame f ;

    public static void main(String[] args) {
        f=new FractalFrame(p,e) ;
    }
}