package edu.umb.cs681.hw14;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.umb.cs681.hw14.fs.util.FSVisitor;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> Children;
	private  AtomicBoolean done =new AtomicBoolean(false);
	
	Directory(Directory parent,String name, int size, LocalDateTime creationTime) {
		super(parent,name, size, creationTime);
		Children=new LinkedList<>();
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
	
	public void appendChild(FSElement element) {
		
		rwLock.writeLock().lock();
		try {
			Children.add(element);
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public LinkedList<FSElement> getChildren(){
		rwLock.readLock().lock();
		try {
			return Children;
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	public int countChildren() {
		rwLock.readLock().lock();
		try {
			return Children.size();
		} finally {
			rwLock.readLock().unlock();
		}
		
	}

	public LinkedList<Directory> getSubDirectories() {
		
		rwLock.readLock().lock();
		try {
			LinkedList<Directory> subDirectories = new LinkedList<>();

		    for (FSElement child : Children) {
		        if (child.isDirectory()) {
		            subDirectories.add((Directory) child);
		        }
		    }

		    return subDirectories;
		} finally {
			rwLock.readLock().unlock();
		}
	    
	}
	
	public LinkedList<File> getFiles() {
		
		rwLock.readLock().lock();
		try {
			LinkedList<File> Files = new LinkedList<>();

		    for (FSElement child : Children) {
		        if (child.isFile()) {
		        	Files.add((File) child);
		        }
		    }

		    return Files;
		} finally {
			rwLock.readLock().unlock();
		}
	    
	}
	
	
	
	@Override
	public boolean isDirectory() {
		rwLock.readLock().lock();
		try {
			return true;
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	
	public int getTotalSize() {
		rwLock.readLock().lock();
		try {
			int totalSize = getSize(); 
	        for (FSElement child : Children) {
	            totalSize += child.getTotalSize();
	        }
	        return totalSize;
		} finally {
			rwLock.readLock().unlock();
		}
        
    }

	@Override
	public boolean isFile() {
		rwLock.readLock().lock();
		try {
			int totalSize = getSize(); 
	        for (FSElement child : Children) {
	            totalSize += child.getTotalSize();
	        }
	        return false;
		} finally {
			rwLock.readLock().unlock();
		}
				
	}
	
	public void accept(FSVisitor v) {
		rwLock.readLock().lock();
		try {
			v.visit(this);
			for(FSElement e: Children) {
				e.accept(v);
			}
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	
	public static void main(String[] args) {
		 Directory[] Directories = new Directory[10];
		 LocalDateTime time=LocalDateTime.now();
		 for(int i=0;i<10;i++) {
			 Directories[i]=new Directory(null,"Directory-"+i,i,time);
		 }
		 
		Thread[] threads = new Thread[10];

      for (int i = 0; i < 10; i++) {
      	final int index = i;
          Thread t = new Thread(() -> {
          	while(!Directories[index].done.get()) {
                  System.out.println(Thread.currentThread().getName()+" running to get Directory Data, " +" Directory name : "+Directories[index].getName()+", Directory size : "+Directories[index].getSize());
                  if(Directories[index].done.get()) break;
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
      	Directories[i].setDone();
      }
      for(int i=0;i<10;i++) {
      	threads[i].interrupt();
      }
      
      
		
	}

	@Override
	public boolean isLink() {
		return false;
	}

}