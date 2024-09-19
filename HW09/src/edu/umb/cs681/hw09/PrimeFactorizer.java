package edu.umb.cs681.hw09;

import java.util.LinkedList;

public class PrimeFactorizer {
    protected long dividend;
    protected LinkedList<Long> factors;
    protected long from;
    protected long to;

    public PrimeFactorizer(long dividend,long from, long to ) {
        if (dividend <= 1) {
            throw new IllegalArgumentException("Dividend must be greater than 1");
        }
        this.dividend = dividend;
        this.from = from;
        this.to = to;
        factors = new LinkedList<>();
    }

    public void generatePrimeFactors() {
        long n = dividend;
        long divisor = from;

        while (n > 1 && divisor <= to) {
            if (n % divisor == 0) {
                factors.add(divisor);
                n /= divisor;
            } else {
                divisor++;
            }
        }
    }

    public LinkedList<Long> getPrimeFactors() {
        return factors;
    }

    public static void main(String[] args) {
        long dividend = 84; 
        PrimeFactorizer factorizer = new PrimeFactorizer(dividend,3,100);
        factorizer.generatePrimeFactors();
        LinkedList<Long> primeFactors = factorizer.getPrimeFactors();
        System.out.println("Prime factors of " + dividend + " are:");
        for (long factor : primeFactors) {
            System.out.print(factor + " ");
        }
    }
}
