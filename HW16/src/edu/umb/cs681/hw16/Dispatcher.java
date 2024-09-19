package edu.umb.cs681.hw16;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

public class Dispatcher{

	 private HashSet<Taxi> availableTaxis;
	 private ReentrantLock lockD =new ReentrantLock();
	 
	 public Dispatcher() {
		 this.availableTaxis=new HashSet<>();
	 }

	 public void notifyAvailable(Taxi t){ 
		 lockD.lock();
		 availableTaxis.add(t); 
		 lockD.unlock();
	 } 
	 
	 public void removeAvailable(Taxi t) {
		    lockD.lock();
		    availableTaxis.remove(t);
		    lockD.unlock();
		}

	 
	 public void displayAvailableTaxis(){
		 HashSet<Taxi> availableTaxisLocal;
		 lockD.lock();
		 availableTaxisLocal = new HashSet<Taxi>(availableTaxis);
		 lockD.unlock();
		 System.out.println(Thread.currentThread().getName()+" Available Taxis : "+ availableTaxis.size());
		 for(Taxi t: availableTaxisLocal) {
			 System.out.println(Thread.currentThread().getName()+ " X : "+t.getLocation().getX()+" Y :"+ t.getLocation().getY());
			 }
	}
	 
	 	
	}