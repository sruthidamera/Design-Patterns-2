package edu.umb.cs681.hw17.fs.util;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.umb.cs681.hw17.Directory;
import edu.umb.cs681.hw17.File;
import edu.umb.cs681.hw17.Link;

public class FileSearchVisitor implements FSVisitor{
	
	String fileName;
	ConcurrentLinkedQueue<File> foundFiles=new ConcurrentLinkedQueue<>();
	
	public FileSearchVisitor(String fileName){
		this.fileName=fileName;
	}
	

	@Override
	public void visit(Link link) {
		return;
		
	}

	@Override
	public void visit(File file) {
		
		if(file.getName().equals(fileName)) {
			foundFiles.add(file);
		}
		
	}

	@Override
	public void visit(Directory directory) {
		return;
		
	}
	
	public ConcurrentLinkedQueue<File> getFoundFiles(){
		return foundFiles;
	}




}

