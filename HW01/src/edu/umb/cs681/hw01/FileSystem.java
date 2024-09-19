package edu.umb.cs681.hw01;

import java.util.LinkedList;

public class FileSystem {
	LinkedList<Directory> rootDirectories;
	private static FileSystem fsObject;
	
	private FileSystem() {
		rootDirectories=new LinkedList<>();
	}
	

    public static FileSystem getFileSystem() {
        if(fsObject == null) {
        	fsObject = new FileSystem();
        }
        return fsObject;
    }

	
	public LinkedList<Directory> getRootDirs() {
		return rootDirectories;
	}
	
	public void appendRootDir(Directory root) {
		rootDirectories.add(root);
	}
}
