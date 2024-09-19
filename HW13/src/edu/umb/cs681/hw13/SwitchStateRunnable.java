package edu.umb.cs681.hw13;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class SwitchStateRunnable implements Runnable{
	private TrafficLight tl;
	private TrafficLightThreadSafe tlts;
	private AtomicBoolean done=new AtomicBoolean(false);
	
	public SwitchStateRunnable(TrafficLight tl) {
		this.tl=tl;
		this.tlts=null;
		
	}
	
	public SwitchStateRunnable(TrafficLightThreadSafe tlts) {
		this.tl=null;
		this.tlts=tlts;
		
	}
	
	public void setDone(boolean doneValue) {
		done.set(doneValue);
	}
	
	public void run(){
		try{
			while(!done.get()){
				if(tlts==null) {
					System.out.println("TrafficLight "+Thread.currentThread()+"(Sw) switched state to : "+ tl.switchState());
				} else {
					System.out.println("TrafficLightThreadSafe "+Thread.currentThread()+"(Sw) switched state to : "+ tlts.switchState());
				}
				if(done.get()) break;
				Thread.sleep( Duration.ofSeconds(2).toMillis() );
				try{
			   		 Thread.sleep(100);
			   		}catch(InterruptedException e){
			   		 break;
			   	}
			}
		}catch(InterruptedException exception){}
	}
}
