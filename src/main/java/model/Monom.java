package model;

public class Monom implements Comparable<Monom>{
    private Integer degree;
    private Double coef;

    public Monom(Integer degree, Double coef) {
        this.degree = degree;
        this.coef = coef;
    }

    public Integer getDegree() {
        return degree;
    }
    public void setDegree(Integer degree) {
        this.degree = degree;
    }
    public Double getCoef() {
        return coef;
    }
    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public int compareTo(Monom o) {
        Integer compareDegree = o.getDegree();
        return compareDegree - this.degree;
    }


}
