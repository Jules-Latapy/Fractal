package pac;

import java.util.function.Function;

public class Equation {

    private final Param p;

    public Equation(Param p) {
        this.p=p;
    }

    public Complex fct(Complex z){

        return (z.multi(z)).minus(new Complex(p.coefRe,p.coefIm)) ;
    }

    public Complex burningShip(Complex z){

        return (new Complex(Math.abs(z.re()),Math.abs(z.im()))).multi(new Complex(Math.abs(z.re()),Math.abs(z.im())))
                .minus(new Complex(p.coefRe,p.coefIm)) ;
    }

    public static boolean stagne(Complex base, Function<Complex,Complex> fct, int limit, int debut, int maxIter) {

        for (int i = debut; i <maxIter; i++) {
            base = fct.apply(base) ;
        }

        return( Math.abs(base.im()) < limit && Math.abs(base.re()) < limit);
    }
}