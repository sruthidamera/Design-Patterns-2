package edu.umb.cs681.hw01.fs.util;

import java.util.LinkedList;

import edu.umb.cs681.hw01.Directory;
import edu.umb.cs681.hw01.File;
import edu.umb.cs681.hw01.Link;

public class FileSearchVisitor implements FSVisitor{
	
	String fileName;
	LinkedList<File> foundFiles=new LinkedList<>();
	
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
	
	public LinkedList<File> getFoundFiles(){
		return foundFiles;
	}

}
