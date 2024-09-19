package edu.umb.cs681.hw17.fs.util;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.umb.cs681.hw17.Directory;
import edu.umb.cs681.hw17.File;
import edu.umb.cs681.hw17.Link;

public class FileCrawlingVisitor implements FSVisitor{
	
	ConcurrentLinkedQueue<File> files=new ConcurrentLinkedQueue<>();

	@Override
	public void visit(Link link) {
		return;
		
	}

	@Override
	public void visit(File file) {
		files.add(file);
		
	}

	@Override
	public void visit(Directory directory) {
		return;
		
	}
	
	public ConcurrentLinkedQueue<File> getFiles(){
		return files;
	}

}

