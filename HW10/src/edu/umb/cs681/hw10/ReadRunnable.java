package edu.umb.cs681.hw10;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadRunnable implements Runnable{
	private BankAccount account;
	private AtomicBoolean done=new AtomicBoolean(false);
	
	public ReadRunnable(BankAccount account) {
		this.account = account;
	}
	public void setDone(boolean doneValue) {
		done.set(doneValue);
	}
	
	public void run(){
		try{
			System.out.println(Thread.currentThread()+" (r) started ");
			for(int i = 0; i < 10; i++){
				if(done.get()) break;
				
				account.getBalance();
				Thread.sleep( Duration.ofSeconds(2).toMillis() );
				
				try{
			   		 Thread.sleep(100);
			   		}catch(InterruptedException e){
			   		 break;
			   	}
			}
			System.out.println(Thread.currentThread()+" (r) ended ");
		}catch(InterruptedException exception){}
	}
}

