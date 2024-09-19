package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator  extends RunnablePrimeGenerator {

	ReentrantLock lock=new ReentrantLock();
	volatile Boolean done =false;
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void setDone() {
			done=true;
	}
	
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
				if(done) break;
				if( isPrime(n) ){ primes.add(n); }
        }
	}
	
	
	public static void main(String[] args) {
		System.out.println("Runnable cancellable prime generator");
		RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(2, 10);
		RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(2, 10);
	    Thread t1 = new Thread(gen1);
	    Thread t2 = new Thread(gen2);
	    t1.start();
	    t2.start();
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    gen1.setDone();
	    System.out.print("Prime generator 1 generated primes : ");
	    gen1.getPrimes().forEach((Long prime) -> System.out.print(prime+" "));
	    System.out.print("\nPrime generator 2 generated primes : ");
	    gen2.getPrimes().forEach((Long prime) -> System.out.print(prime+" "));
	}
	
}
