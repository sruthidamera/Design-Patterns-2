package edu.umb.cs681.hw13;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class SetStateRunnable implements Runnable{
	private TrafficLight tl;
	private TrafficLightThreadSafe tlts;
	private AtomicBoolean done=new AtomicBoolean(false);
	
	public SetStateRunnable(TrafficLight tl) {
		this.tl=tl;
		this.tlts=null;
	}
	
	public SetStateRunnable(TrafficLightThreadSafe tlts) {
		this.tl=null;
		this.tlts=tlts;
	}
	public void setDone(boolean doneValue) {
		done.set(doneValue);
	}
	
	public void run(){
		try{
			while(!done.get()){
				Random random=new Random();
				
	            if (tlts==null) {
	            	System.out.println("TrafficLight "+Thread.currentThread()+"(S) Set State to : "+tl.setState(TrafficLightState.RED));}
	            else { 
	            	System.out.println("TrafficLightThreadSafe "+Thread.currentThread()+"Set State to : "+tlts.setState(TrafficLightState.RED));
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
