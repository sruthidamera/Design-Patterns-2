package edu.umb.cs681.hw08;

import java.util.LinkedList;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable{
	public RunnablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}

	@Override
	public void run() {
		this.generatePrimeFactors();
	}

	public static void main(String[] args) {
		RunnablePrimeFactorizer r1 = new RunnablePrimeFactorizer(84L, 2L, 100L);
		RunnablePrimeFactorizer r2 = new RunnablePrimeFactorizer(84L, 5L, 100L);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start(); 
		t2.start();
		System.out.print("Factorizer 1 generated factors : ");
		r1.getPrimeFactors().forEach((Long factor) -> System.out.print(factor+" "));
		System.out.print("\nFactorizer 2 generated factors : ");
		r2.getPrimeFactors().forEach((Long factor) -> System.out.print(factor+" "));
    }
}

