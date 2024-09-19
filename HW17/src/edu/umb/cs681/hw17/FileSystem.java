package edu.umb.cs681.hw17;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
	ConcurrentLinkedQueue<Directory> rootDirectories;
	private static FileSystem fsObject;
	private static ReentrantLock lock=new ReentrantLock();
	private static AtomicBoolean done =new AtomicBoolean(false);
	private static AtomicReference<FileSystem> afObj=new AtomicReference<>();
	private FileSystem() {
		rootDirectories=new ConcurrentLinkedQueue<>();
	}
	

    public static FileSystem getFileSystem() {
    	lock.lock();
    	try {
	        if(fsObject == null) {
	        	afObj.set(new FileSystem());
	        	fsObject = afObj.get();
	        } else {
	        	fsObject=afObj.get();
	        }
	        return fsObject;
    	}finally {
    		lock.unlock();
    	}
    }
    
    public void setDone() {
		lock.lock();
		try {
			done.set(true);
		}finally {
			lock.unlock();
		}
	}

	
	public ConcurrentLinkedQueue<Directory> getRootDirs() {
		lock.lock();
		try {
			return rootDirectories;
		}finally {
			lock.unlock();
		}
		
	}
	
	public void appendRootDir(Directory root) {
		lock.lock();
		try {
			rootDirectories.add(root);
		}finally {
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		 FileSystem[] fileSystems = new FileSystem[10];
		 for(int i=0;i<10;i++) {
			 fileSystems[i]=FileSystem.getFileSystem();
		 }
		 
		Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
        	final int index = i;
            Thread t = new Thread(() -> {
            	while(!fileSystems[index].done.get()) {
                    
                    System.out.println(Thread.currentThread().getName()+" running to get FS Data, root directories length : " + fileSystems[index].getRootDirs().size()); 
                    if(fileSystems[index].done.get()) break;
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
        	fileSystems[i].setDone();
        }
        for(int i=0;i<10;i++) {
        	threads[i].interrupt();
        }
        
        
		
	}
	
	
	
	
	
}