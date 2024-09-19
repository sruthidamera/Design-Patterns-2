package edu.umb.cs681.hw01;

import java.time.LocalDateTime;
import java.util.LinkedList;

import edu.umb.cs681.hw01.fs.util.FSVisitor;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> Children;

	Directory(Directory parent,String name, int size, LocalDateTime creationTime) {
		super(parent,name, size, creationTime);
		Children=new LinkedList<>();
		
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
        	if(child.isDirectory()) {
            totalSize += ((Directory) child).getTotalSize();
        	}else {
        		 totalSize += child.getSize();
        	}
        }
        return totalSize;
    }
	
	public boolean isLink() {
		
		return false;
	}

	public boolean isFile() {
		return false;
	}

	public void accept(FSVisitor v) {
		v.visit(this);
		for(FSElement e: Children) {
			e.accept(v);
		}
		
	}
	

}
