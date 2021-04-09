package gui;

import exceptions.InvalidInputException;
import model.Monom;
import model.Polynomial;
import operations.Operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    private String output;
    private String[] monomials;
    private final Character variable = 'x';
    private Polynomial poly;
    public Model() {
    }

    public String[] getMonomes() {
        return monomials;
    }

    public Polynomial getPoly() {
        return poly;
    }

    private void getMonomes(String inputPolynomial) throws InvalidInputException {
        if(inputPolynomial == null) {
            throw new InvalidInputException("A field is empty");
        }
        try {
            inputPolynomial = inputPolynomial.replaceAll("\\s+", "");
            // Excess white spaces are cut out from input string
            System.out.println("init string: " + inputPolynomial);
            inputPolynomial = inputPolynomial.replaceAll("^[+][a-zA-Z]", "1" + variable);
            inputPolynomial = inputPolynomial.replaceAll("^[-][a-zA-Z]", "-1" + variable);
            inputPolynomial = inputPolynomial.replaceAll("(?<!^)[+][a-zA-Z]", " +1" + variable);
            inputPolynomial = inputPolynomial.replaceAll("(?<!^)[-][a-zA-Z]", " -1" + variable);
            inputPolynomial = inputPolynomial.replaceAll("^[a-zA-Z]", "1" + variable);
            inputPolynomial = inputPolynomial.replaceAll("(?<!^)\\+", " +");
            inputPolynomial = inputPolynomial.replaceAll("(?<!^)\\-", " -");
            // A single white space is added after + or -
            Matcher matcher = Pattern.compile("^\\d").matcher(inputPolynomial);
            if(matcher.find()) {
                inputPolynomial = "+" + inputPolynomial;
            }
            // Puts a sign in front of the first number
            inputPolynomial = inputPolynomial.replaceAll("[a-zA-Z]\\s", "x^1 ");
            // if we have a variable and a white space right after, we replace it with the exponent 1
            matcher.reset();
            matcher = Pattern.compile("[^\\s\\^][+-]?\\d+$").matcher(inputPolynomial);
            if(matcher.find()) {
                inputPolynomial += "x^0";
            }
            inputPolynomial += " ";
            // A white space is added at the end so the split can be made
            monomials = inputPolynomial.split("\\s+");
            System.out.println("Poly ready to split: " + inputPolynomial);
            for(int e = 0; e < monomials.length; e++) {
                monomials[e] = monomials[e].replaceAll("[a-zA-Z]\\^?", " ");
            }
        }
        catch(NumberFormatException e){
            throw new InvalidInputException("Invalid Input");
        }
    }

    private Polynomial convertToPolynomial(String inputPolynomial) {
        Polynomial workingPolynomial = new Polynomial();
        getMonomes(inputPolynomial);
        for(String s : monomials) {
            workingPolynomial.add(new Monom(Integer.parseInt(s.substring(s.indexOf(' ') + 1)),
                    Double.parseDouble(s.substring(0, s.indexOf(' ')))));
        }
        return workingPolynomial;
    }

    public String getPolynome() {
        setOutputString();
        System.out.println(output);
        return this.output;
    }

    private void setOutputString() {
        this.output = null;
        for(Integer k : poly.getPoly().keySet()){
            if(output == null) {
                output = poly.getPoly().get(k).getCoef() + "x^"
                        + poly.getPoly().get(k).getDegree();
            }
            else if(poly.getPoly().get(k).getCoef() < 0.0 || (output == null && poly.getPoly().get(k).getCoef() < 0.0f)){
                output += " " + poly.getPoly().get(k).getCoef() + "x^"
                        + poly.getPoly().get(k).getDegree();
            }
            else {
                output += " + " + poly.getPoly().get(k).getCoef() + "x^"
                        + poly.getPoly().get(k).getDegree();
            }
        }

        System.out.println("output var is: " + output);
    }

    public void add(String polyString1, String polyString2) {
        Polynomial poly1 = convertToPolynomial(polyString1);
        Polynomial poly2 = convertToPolynomial(polyString2);
        this.poly = Operations.add(poly1, poly2);
    }

    public void subtract(String polyString1, String polyString2) {
        Polynomial poly1 = convertToPolynomial(polyString1);
        Polynomial poly2 = convertToPolynomial(polyString2);
        this.poly = Operations.subtract(poly1, poly2);
    }

    public void multiply(String polyString1, String polyString2) {
        Polynomial poly1 = convertToPolynomial(polyString1);
        Polynomial poly2 = convertToPolynomial(polyString2);
        this.poly = Operations.multiply(poly1, poly2);
    }

    public void division(String polyString1, String polyString2) {
        Polynomial poly1 = convertToPolynomial(polyString1);
        Polynomial poly2 = convertToPolynomial(polyString2);
        this.poly = Operations.division(poly1, poly2);
    }

    public void derivate(String polyString1) {
        Polynomial poly1 = convertToPolynomial(polyString1);
        this.poly = Operations.derive(poly1);
    }

    public void integrate(String polyString1) {
        Polynomial poly1 = convertToPolynomial(polyString1);
        this.poly = Operations.integrate(poly1);
    }
}