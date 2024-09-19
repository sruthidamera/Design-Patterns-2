package edu.umb.cs681.hw09;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable{

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	@Override
	public void run() {
		generatePrimes();
		
	}
	
	public static void main(String[] args) {
		System.out.println("Runnable prime generator");
		RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(2, 10);
		RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(2, 10);
	    Thread t1 = new Thread(gen1);
	    Thread t2 = new Thread(gen2);
	    t1.start();
	    t2.start();
	    System.out.print("Prime generator 1 generated primes : ");
	    gen1.getPrimes().forEach((Long prime) -> System.out.print(prime+" "));
	    System.out.print("\nPrime generator 2 generated primes : ");
	    gen2.getPrimes().forEach((Long prime) -> System.out.print(prime+" "));
	}


}
