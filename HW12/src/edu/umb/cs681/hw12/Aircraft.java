package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Aircraft implements Runnable{
	private Position position; 
	private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
	
	public Aircraft(Position pos){ 
		this.position = pos; 
	}
	
	public void setPosition(double newLat, double newLong, double newAlt){
		rwLock.writeLock().lock();
		try {
			this.position = this.position.change(newLat, newLong, newAlt);
		} finally {
			rwLock.writeLock().unlock();
		}
	}
		
	public Position getPosition(){
		rwLock.readLock().lock();
		try {
			return position;
		} finally {
			rwLock.readLock().unlock();
		}		 
	}

		@Override
	public void run() {
		setPosition(0, 0, 0);
		System.out.println(Thread.currentThread().getName() + " has set the position, longitude : 0, latitude : 0, altitude : 0");
		Position p=getPosition();
		System.out.println(Thread.currentThread().getName() + " has read the position longitude :"+ p.longitude()+", latitude :"+p.latitude()+", altitude :"+p.altitude());	
	} 
		
		
		
	public static void main(String args[]) {
		Position p =new Position(1,1,1);
		Aircraft ac1=new Aircraft(p);
		Aircraft ac2=new Aircraft(p);
		Aircraft ac3=new Aircraft(p);
		Aircraft ac4=new Aircraft(p);
		Aircraft ac5=new Aircraft(p);
		Aircraft ac6=new Aircraft(p);
		Aircraft ac7=new Aircraft(p);
		
		Thread t1=new Thread(ac1);
		Thread t2=new Thread(ac2);
		Thread t3=new Thread(ac3);
		Thread t4=new Thread(ac4);
		Thread t5=new Thread(ac5);
		Thread t6=new Thread(ac6);
		Thread t7=new Thread(ac7);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	}
