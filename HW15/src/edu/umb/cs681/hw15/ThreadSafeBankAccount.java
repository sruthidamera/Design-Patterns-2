package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();

	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (d): " + balance);
			balance = balance + amount;
			System.out.println(", New balance (d): " + balance);
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			balance = balance - amount;
			System.out.println(", New balance (w): " + balance);
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() {
		lock.lock();
		try {
			return this.balance;
		} finally {
			lock.unlock();
		}
		}
	
	public static void main(String args[]) {
		ThreadSafeBankAccount bank=new ThreadSafeBankAccount();
		WithdrawRunnable w1=new WithdrawRunnable(bank);
		WithdrawRunnable w2=new WithdrawRunnable(bank);
		DepositRunnable d1=new DepositRunnable(bank);
		DepositRunnable d2=new DepositRunnable(bank);
		ReadRunnable r1=new ReadRunnable(bank);
		ReadRunnable r2=new ReadRunnable(bank);
		ReadRunnable r3=new ReadRunnable(bank);
		Thread t1=new Thread(w1);
		Thread t2=new Thread(w2);
		Thread t3=new Thread(d1);
		Thread t4=new Thread(d2);
		Thread t5=new Thread(r1);
		Thread t6=new Thread(r2);
		Thread t7=new Thread(r3);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		w1.setDone(true);
		w2.setDone(true);
		d1.setDone(true);
		d2.setDone(true);
		r1.setDone(true);
		r2.setDone(true);
		r3.setDone(true);
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		t7.interrupt();
		
		
	}
}
