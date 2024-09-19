package edu.umb.cs681.hw10;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private AtomicBoolean done=new AtomicBoolean(false);
	
	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}
	public void setDone(boolean doneValue) {
		done.set(doneValue);
	}
	
	public void run(){
		try{
			System.out.println(Thread.currentThread()+" (w) started ");
			for(int i = 0; i < 10; i++){
				if(done.get()) break;
				account.withdraw(100);
				Thread.sleep( Duration.ofSeconds(2).toMillis() );
				
				try{
			   		 Thread.sleep(100);
			   		}catch(InterruptedException e){
			   		 break;
			   	}
				
			}
			System.out.println(Thread.currentThread()+" (w) ended ");
		}catch(InterruptedException exception){}
	}
}