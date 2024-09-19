package edu.umb.cs681.hw07;


import java.time.LocalDateTime;

public class File extends FSElement{

	File(Directory parent,String name, int size, LocalDateTime creationTime) {
		super(parent,name, size, creationTime);
		if(parent!=null) {
		parent.appendChild(this);
		}
	}

	@Override
	public boolean isDirectory() {
		return false;
	}
	
	public int getTotalSize() {
        return getSize();
    }

	@Override
	public boolean isFile() {
		return true;
	}
}
