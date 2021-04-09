package operations;

import model.*;
public abstract class Operations {

    public static Polynomial add(Polynomial poly1, Polynomial poly2) {
        Polynomial finalPoly = new Polynomial();
        finalPoly.setPoly(poly1.getPoly());
        for (Integer k : poly2.getPoly().keySet()) {
            finalPoly.add(poly2.getPoly().get(k));
        }
        return finalPoly;
    }

    public static Polynomial subtract(Polynomial poly1, Polynomial poly2) {
        Polynomial finalPoly = new Polynomial();
        finalPoly.setPoly(poly1.getPoly());
        for (Integer k : poly2.getPoly().keySet()) {

            double aux = poly2.getPoly().get(k).getCoef() * -1.0d;
            poly2.getPoly().get(k).setCoef(aux);

            finalPoly.add(poly2.getPoly().get(k));

            aux = poly2.getPoly().get(k).getCoef() * -1.0d;
            poly2.getPoly().get(k).setCoef(aux);
        }
        return finalPoly;
    }

    public static Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        Polynomial finalPoly = new Polynomial();
        Polynomial auxPoly = new Polynomial();
        Monom auxMonomial;
        for(Integer key : poly2.getPoly().keySet()) {
            auxMonomial = poly2.getPoly().get(key);
            for(Integer k : poly1.getPoly().keySet()) {
                auxPoly.add(new Monom(auxMonomial.getDegree() + poly1.getPoly().get(k).getDegree(),
                        auxMonomial.getCoef() * poly1.getPoly().get(k).getCoef()));
            }
            finalPoly = Operations.add(finalPoly, auxPoly);
            auxPoly.clear();
        }
        return finalPoly;
    }

    public static Polynomial division(Polynomial poly1, Polynomial poly2){
        // we check if poly 2 is null in "Model" from 'gui' package
//        Monom q = new Monom(0, 0.0);
//        Polynomial r = poly1;
//
//        while(!r.isEmpty() && (r.highestDegree() > poly2.highestDegree())){
//
//        }


        return null;
    }

    public static Polynomial derive(Polynomial poly1) {
        Integer degree;
        for(Integer k : poly1.getPoly().keySet()) {
            degree = poly1.getPoly().get(k).getDegree();
            if(degree <= 0) {
                poly1.getPoly().get(k).setCoef(0.0d);
            }else {
                Double aux = poly1.getPoly().get(k).getCoef() * poly1.getPoly().get(k).getDegree();
                poly1.getPoly().get(k).setCoef(aux);
                poly1.getPoly().get(k).setDegree(--degree);
            }
        }
        return poly1;
    }

    public static Polynomial integrate(Polynomial poly1) {
        Integer degree;
        for(Integer k : poly1.getPoly().keySet()) {
            degree = poly1.getPoly().get(k).getDegree();
            Double aux = poly1.getPoly().get(k).getCoef() / (poly1.getPoly().get(k).getDegree() + 1);
            poly1.getPoly().get(k).setCoef(aux);
            poly1.getPoly().get(k).setDegree(++degree);
        }
        return poly1;
    }
}
