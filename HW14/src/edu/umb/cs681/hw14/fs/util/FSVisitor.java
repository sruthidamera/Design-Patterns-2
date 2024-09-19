package edu.umb.cs681.hw14.fs.util;

import edu.umb.cs681.hw14.Directory;
import edu.umb.cs681.hw14.File;
import edu.umb.cs681.hw14.Link;

public interface  FSVisitor {
	
	public void visit(Link link);
	public void visit(File file);
	public void visit(Directory directory);

}
