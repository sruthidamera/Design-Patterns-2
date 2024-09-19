package edu.umb.cs681.hw15;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	private AtomicBoolean done=new AtomicBoolean(false);
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}
	public void setDone(boolean doneValue) {
		done.set(doneValue);
	}
	
	public void run(){
		try{
			System.out.println(Thread.currentThread()+" (d) started ");
			for(int i = 0; i < 10; i++){
				if(done.get()) break;
				account.deposit(100);
				Thread.sleep( Duration.ofSeconds(2).toMillis() );
				
				try{
			   		 Thread.sleep(100);
			   		}catch(InterruptedException e){
			   		 break;
			   	}
			}
			System.out.println(Thread.currentThread()+" (d) ended ");
		}catch(InterruptedException exception){}
	}
}

