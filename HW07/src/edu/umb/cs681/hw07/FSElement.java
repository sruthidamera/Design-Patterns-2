package edu.umb.cs681.hw07;



import java.time.LocalDateTime;

public abstract class FSElement {
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private Directory parent;
	
	public FSElement(Directory parent,String name, int size, LocalDateTime creationTime){
		this.name=name;
		this.size=size;
		this.creationTime=creationTime;
		this.parent=parent;
	}
	
	public Directory getParent() {
		return parent;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getName() {
		return name;
	}
	
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public abstract boolean isDirectory();
	
	public abstract boolean isFile();
	
	public abstract int getTotalSize();
	
}