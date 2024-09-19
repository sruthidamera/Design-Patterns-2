package edu.umb.cs681.hw17;


import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.umb.cs681.hw17.fs.util.FSVisitor;

public class File extends FSElement{

	
	private  AtomicBoolean done =new AtomicBoolean(false);
	
	File(Directory parent,String name, int size, LocalDateTime creationTime) {
		super(parent,name, size, creationTime);
		if(parent!=null) {
		parent.appendChild(this);
		}
	}

	public void setDone() {
		rwLock.writeLock().lock();
		try {
			done.set(true);
		}finally {
			rwLock.writeLock().unlock();
		}
	}
	
	@Override
	public boolean isDirectory() {
		rwLock.readLock().lock();
		try {
			return false;
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public int getTotalSize() {
		rwLock.readLock().lock();
		try {
			return getSize();
		} finally {
			rwLock.readLock().unlock();
		}
        
    }

	@Override
	public boolean isFile() {
		rwLock.readLock().lock();
		try {
			return true;
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	@Override
	public boolean isLink() {
		return false;
	}
	
	public void accept(FSVisitor v) {
		rwLock.readLock().lock();
		try {
			v.visit(this);
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public static void main(String[] args) {
		 File[] files = new File[10];
		 LocalDateTime time=LocalDateTime.now();
		 for(int i=0;i<10;i++) {
			 files[i]=new File(null,"file-"+i,i,time);
		 }
		 
		Thread[] threads = new Thread[10];

       for (int i = 0; i < 10; i++) {
       	final int index = i;
           Thread t = new Thread(() -> {
           	while(!files[index].done.get()) {
                   System.out.println(Thread.currentThread().getName()+" running to get file Data, " +" file name : "+files[index].getName()+", file size : "+files[index].getSize());
                   if(files[index].done.get()) break;
                   try {
       	    			Thread.sleep(100);
       	    		}catch(InterruptedException e) {
       	    			System.out.println(Thread.currentThread().getName()+" interrupted.");
       	    			return;
       	    	}
           	}
           	});
           
           t.start();
           threads[i] = t;
       }
       
       try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
       
       for(int i=0;i<10;i++) {
       	files[i].setDone();
       }
       for(int i=0;i<10;i++) {
       	threads[i].interrupt();
       }
       
       
		
	}

	
}
