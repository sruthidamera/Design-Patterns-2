package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;

public class Taxi implements Runnable{ 
	private Point location; 
	private Point dest;
	private Dispatcher dispatcher;
	private volatile boolean done;
	private ReentrantLock lockT =new ReentrantLock();
	
	
	public Taxi(Dispatcher dispatcher, Point dest) {
		this.dispatcher=dispatcher;
		this.dest=dest;
		done=false;
		
	}
	
	public void setDone() {
		done=true;
	}
	public void setLocation(Point loc){
		lockT.lock();
		try{
			location = loc; 
			if(!(location.getX()==dest.getX()&&location.getY()==dest.getY())) {
				dispatcher.removeAvailable(this);
				return;
				}
		}finally{ 
			lockT.unlock(); 
		}
		dispatcher.notifyAvailable(this);                                    
		}

	public void run(){ 
		while(true){
			if(done) break;
			setLocation(getGPSLoc());
			dispatcher.displayAvailableTaxis();
			try{
		   		Thread.sleep(100);
		   		}catch(InterruptedException e){
		   		 break;
		   	}
		}
		}
	
	private Point getGPSLoc() {
        double x = (int)(Math.random() * 2); 
        double y = (int)(Math.random() * 2);


        return new Point(x, y);
    }

	public Point getLocation() {
		lockT.lock();
		try {
			return location;
		}finally {
			lockT.unlock();
		}
	}
	
	public static void main(String args[]) {
		
		Point dest=new Point(1,1);
		Dispatcher disp=new Dispatcher();
		Taxi tx1=new Taxi(disp,dest);
		Taxi tx2=new Taxi(disp,dest);
		Taxi tx3=new Taxi(disp,dest);
		Thread t1=new Thread(tx1);
		Thread t2=new Thread(tx2);
		Thread t3=new Thread(tx3);
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tx1.setDone();
		tx2.setDone();
		tx3.setDone();
		
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		
		
	}
}
