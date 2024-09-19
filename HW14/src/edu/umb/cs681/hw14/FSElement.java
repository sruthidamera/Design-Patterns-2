package edu.umb.cs681.hw14;



import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.umb.cs681.hw14.fs.util.FSVisitor;

public abstract class FSElement {
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private Directory parent;
	protected ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public FSElement(Directory parent,String name, int size, LocalDateTime creationTime){
		this.name=name;
		this.size=size;
		this.creationTime=creationTime;
		this.parent=parent;
	}
	
	public Directory getParent() {
		rwLock.readLock().lock();
		try {
			return parent;
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	public int getSize() {
		rwLock.readLock().lock();
		try {
			return size;
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	public String getName() {
		rwLock.readLock().lock();
		try {
			return name;
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	
	public LocalDateTime getCreationTime() {
		rwLock.readLock().lock();
		try {
			return creationTime;
		} finally {
			rwLock.readLock().unlock();
		}
		
	}
	public abstract boolean isDirectory();
	
	public abstract boolean isFile();
	
	public abstract boolean isLink();
	
	public abstract int getTotalSize();
	
	public abstract void accept(FSVisitor v);
	
}