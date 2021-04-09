package model;

import java.util.*;
public class Polynomial {
    // Map-ul are Integer pentru grad si Monom pentru elementul din treemap
    private Map<Integer, Monom> poly = new TreeMap<Integer, Monom>();

    public Polynomial() {
    }

    public Polynomial(Map<Integer, Monom> poly) {
        this.poly = poly;
    }

    public Map<Integer, Monom> getPoly() {
        return poly;
    }

    public void setPoly(Map<Integer, Monom> poly) {
        this.poly = poly;
    }

    public Integer highestDegree() {
        Map.Entry<Integer, Monom> maxEntry = null;
        for(Map.Entry<Integer, Monom> entry : poly.entrySet()){
            if(maxEntry == null || entry.getValue().getDegree() > maxEntry.getValue().getDegree()){
                maxEntry = entry;
            }
        }
        return maxEntry.getValue().getDegree();
    }

    public boolean isEmpty(){
        return this.poly.isEmpty();
    }

    public void add(Monom m) {
        if(this.poly.containsKey(m.getDegree())) {
            // we have to add the coef to the element that already exists in the hashmap
            Double aux = this.poly.get(m.getDegree()).getCoef();
            this.poly.replace(m.getDegree(),new Monom (m.getDegree(), aux + m.getCoef()));
        }
        else {
            this.poly.put(m.getDegree(), m);
        }
        if(this.poly.get(m.getDegree()).getCoef() == 0) {
            this.poly.remove(m.getDegree());
        }
    }

    public void clear() {
        this.poly.clear();
    }

    public void printPoly() {
        for(Integer k : this.poly.keySet()) {
            if(this.poly.get(k).getDegree() < 0.0f){
                System.out.print(" - " + this.poly.get(k).getCoef() + "x^" + this.poly.get(k).getDegree());
            }
            else
                System.out.print(" + " + this.poly.get(k).getCoef() + "x^" + this.poly.get(k).getDegree());
        }
        System.out.println(" ");
    }
}
