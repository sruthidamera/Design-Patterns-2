package edu.umb.cs681.hw17;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.umb.cs681.hw17.fs.util.FSVisitor;

public class Link extends FSElement{

	protected String name;
	protected int size;
	protected LocalDateTime creationTime;
	protected Directory parent;
	protected FSElement target=null;
	private  AtomicBoolean done =new AtomicBoolean(false);
	
	Link(Directory parent, String name,int size, LocalDateTime creationTime, FSElement target ){
		super(parent, name, size, creationTime);
		this.name=name;
		this.parent=parent;
		this.size=size;
		this.creationTime=creationTime;
		this.target=target;
		
		
	}
	
	public void setDone() {
		rwLock.writeLock().lock();
		try {
			done.set(true);
		}finally {
			rwLock.writeLock().unlock();
		}
	}

	public FSElement getTarget() {
		rwLock.readLock().lock();
		try {
			return target;
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public boolean isDirectory() {
		rwLock.readLock().lock();
		try {
			return false;
		} finally {
			rwLock.readLock().unlock();
		}
	    
	}
	
	public boolean isLink() {
		rwLock.readLock().lock();
		try {
			return true;
		} finally {
			rwLock.readLock().unlock();
		}
	    
	}
	
	public boolean isFile() {
		rwLock.readLock().lock();
		try {
			return false;
		} finally {
			rwLock.readLock().unlock();
		}
	}


	@Override
	public int getTotalSize() {
		return 0;
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
		 Link[] links = new Link[10];
		 LocalDateTime time=LocalDateTime.now();
		 File f=new File(null,"file",1,time);
		 for(int i=0;i<10;i++) {
			 links[i]=new Link(null,"link-"+i,i,time,f);
		 }
		 
		Thread[] threads = new Thread[10];

     for (int i = 0; i < 10; i++) {
     	final int index = i;
         Thread t = new Thread(() -> {
         	while(!links[index].done.get()) {
                 System.out.println(Thread.currentThread().getName()+" running to get links Data, " +" target name : "+links[index].getTarget().getName());
                 if(links[index].done.get()) break;
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
     	links[i].setDone();
     }
     for(int i=0;i<10;i++) {
     	threads[i].interrupt();
     }
     
     
		
	}

	
	
	
	
	
	
}

