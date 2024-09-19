package edu.umb.cs681.hw01.fs.util;

import java.util.LinkedList;
import java.util.stream.Stream;

import edu.umb.cs681.hw01.Directory;
import edu.umb.cs681.hw01.File;
import edu.umb.cs681.hw01.Link;

public class FileCrawlingVisitor implements FSVisitor{
	
	LinkedList<File> files=new LinkedList<>();

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
	
	public LinkedList<File> getFiles(){
		return files;
	}
	
	public Stream<File> files(){
		return files.stream();
	}

}
