package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;



public class TrafficLightThreadSafe extends TrafficLight {
	private ReentrantLock lock = new ReentrantLock();

    public TrafficLightThreadSafe() {
    	this.state=TrafficLightState.RED;
    }

    public TrafficLightState setState(TrafficLightState newState) {
    	lock.lock();
    	try {
    		this.state = newState;
    		return this.state;
    	}finally {
    		lock.unlock();
    	}
    }

    public TrafficLightState getState() {
    	lock.lock();
    	try {
    		return this.state;
    	} finally {
    		lock.unlock();
    	}
    }

    public TrafficLightState switchState() {
    	try {
    	lock.lock();
        switch (state) {
            case RED:
                setState(TrafficLightState.GREEN);
                return TrafficLightState.GREEN;
            case GREEN:
                setState(TrafficLightState.YELLOW);
                return TrafficLightState.YELLOW;
            case YELLOW:
                setState(TrafficLightState.RED);
                return TrafficLightState.RED;
            default:
                return  TrafficLightState.NONE;
            	
        }
    	} finally {
    		lock.unlock();
    	}
		
    }
    
    public static void main(String args[]) {
    	TrafficLightThreadSafe tl=new TrafficLightThreadSafe();
    	SwitchStateRunnable sw1=new SwitchStateRunnable(tl);
    	SwitchStateRunnable sw2=new SwitchStateRunnable(tl);
    	GetStateRunnable g1=new GetStateRunnable(tl);
    	GetStateRunnable g2=new GetStateRunnable(tl);
    	SetStateRunnable s1=new SetStateRunnable(tl);
    	SetStateRunnable s2=new SetStateRunnable(tl);
		Thread t1=new Thread(sw1);
		Thread t2=new Thread(sw2);
		Thread t3=new Thread(g1);
		Thread t4=new Thread(g2);
		Thread t5=new Thread(s1);
		Thread t6=new Thread(s2);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sw1.setDone(true);
		sw2.setDone(true);
		g1.setDone(true);
		g2.setDone(true);
		s1.setDone(true);
		s2.setDone(true);
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		
		
	}
    
}
