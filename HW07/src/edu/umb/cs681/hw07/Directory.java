package edu.umb.cs681.hw07;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> Children;

	Directory(Directory parent,String name, int size, LocalDateTime creationTime) {
		super(parent,name, size, creationTime);
		Children=new LinkedList<>();
		if(parent!=null) {
		parent.appendChild(this);
		}
	}
	
	public void appendChild(FSElement element) {
		Children.add(element);
	}
	
	public LinkedList<FSElement> getChildren(){
		return Children;
	}
	
	public int countChildren() {
		return Children.size();
	}

	public LinkedList<Directory> getSubDirectories() {
	    LinkedList<Directory> subDirectories = new LinkedList<>();

	    for (FSElement child : Children) {
	        if (child.isDirectory()) {
	            subDirectories.add((Directory) child);
	        }
	    }

	    return subDirectories;
	}
	
	public LinkedList<File> getFiles() {
	    LinkedList<File> Files = new LinkedList<>();

	    for (FSElement child : Children) {
	        if (child.isFile()) {
	        	Files.add((File) child);
	        }
	    }

	    return Files;
	}
	
	
	
	@Override
	public boolean isDirectory() {
		return true;
	}
	
	public int getTotalSize() {
        int totalSize = getSize(); 
        for (FSElement child : Children) {
            totalSize += child.getTotalSize();
        }
        return totalSize;
    }

	@Override
	public boolean isFile() {
				return false;
	}
	

}