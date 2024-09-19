package edu.umb.cs681.hw01.fs.util;

import edu.umb.cs681.hw01.Directory;
import edu.umb.cs681.hw01.File;
import edu.umb.cs681.hw01.Link;

public interface  FSVisitor {
	
	public void visit(Link link);
	public void visit(File file);
	public void visit(Directory directory);

}
