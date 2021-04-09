package operations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Monom;
import model.Polynomial;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.TreeMap;

class OperationsTest {

    boolean CompareTwoTreeMaps(Map<Integer, Monom> p1, Map<Integer, Monom> p2){
        if(p1.equals(p2)){
            return true;
        }
        else
            return false;
    }


    @org.junit.jupiter.api.Test
    @Test
    void add() {
        try{
            Map<Integer, Monom> poly = new TreeMap<Integer, Monom>();
            poly.put(1, new Monom(1, 3.0));
            poly.put(2, new Monom(2, 4.0));
            poly.put(3, new Monom(3, 6.0));
            Polynomial p1 = new Polynomial(poly);
            Polynomial p2 = new Polynomial(poly);
            Polynomial tmp = Operations.add(p1, p2);

            poly.put(1, new Monom(1, 6.0));
            poly.put(2, new Monom(2, 8.0));
            poly.put(3, new Monom(3, 12.0));
            Polynomial eq = new Polynomial(poly);

            System.out.println(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
            assertTrue(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
        }catch(Exception e){
            System.out.println("Some error");
        }
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        try{
            Map<Integer, Monom> poly = new TreeMap<Integer, Monom>();
            poly.put(1, new Monom(1, 3.0));
            poly.put(2, new Monom(2, 4.0));
            poly.put(3, new Monom(3, 6.0));
            Polynomial p1 = new Polynomial(poly);
            poly.put(1, new Monom(1, 2.0));
            poly.put(2, new Monom(2, 5.0));
            poly.put(3, new Monom(3, 4.0));
            Polynomial p2 = new Polynomial(poly);
            Polynomial tmp = Operations.subtract(p1, p2);

            poly.put(1, new Monom(1, 1.0));
            poly.put(2, new Monom(2, -1.0));
            poly.put(3, new Monom(3, 2.0));
            Polynomial eq = new Polynomial(poly);

            System.out.println(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
            assertTrue(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
        }catch(Exception e){
            System.out.println("Some error");
        }
    }

    @org.junit.jupiter.api.Test
    void multiply() {

        try{
            Map<Integer, Monom> poly = new TreeMap<Integer, Monom>();
            Map<Integer, Monom> poly1 = new TreeMap<Integer, Monom>();
            Map<Integer, Monom> poly2 = new TreeMap<Integer, Monom>();
            poly.put(1, new Monom(1, 3.0));
            poly.put(2, new Monom(2, 4.0));
            Polynomial p1 = new Polynomial(poly);
            poly1.put(1, new Monom(1, 2.0));
            Polynomial p2 = new Polynomial(poly);
            Polynomial tmp = Operations.multiply(p1, p2);

            poly2.put(2, new Monom(2, 6.0));
            poly2.put(3, new Monom(3, 8.0));
            Polynomial eq = new Polynomial(poly);

            assertTrue(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
        }catch(Exception e){
            System.out.println("Some error");
        }

    }

    @org.junit.jupiter.api.Test
    void division() {
    }

    @org.junit.jupiter.api.Test
    void derive() {
        Map<Integer, Monom> poly = new TreeMap<Integer, Monom>();
        Map<Integer, Monom> poly1 = new TreeMap<Integer, Monom>();
        poly.put(1, new Monom(1, 3.0));
        poly.put(2, new Monom(2, 4.0));
        poly.put(3, new Monom(3, 6.0));
        Polynomial tmp = Operations.derive(new Polynomial(poly));

        poly1.put(1, new Monom(0, 3.0));
        poly1.put(2, new Monom(1, 8.0));
        poly1.put(3, new Monom(2, 18.0));
        Polynomial eq = new Polynomial(poly1);
        assertTrue(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
    }

    @org.junit.jupiter.api.Test
    void integrate() {
        Map<Integer, Monom> poly = new TreeMap<Integer, Monom>();
        Map<Integer, Monom> poly1 = new TreeMap<Integer, Monom>();
        poly.put(1, new Monom(1, 2.0));
        poly.put(2, new Monom(2, 3.0));
        poly.put(3, new Monom(3, 4.0));
        Polynomial tmp = Operations.integrate(new Polynomial(poly));

        poly1.put(1, new Monom(2, 1.0));
        poly1.put(2, new Monom(3, 1.0));
        poly1.put(3, new Monom(4, 1.0));
        Polynomial eq = new Polynomial(poly1);
        assertTrue(CompareTwoTreeMaps(tmp.getPoly(), eq.getPoly()));
    }
}